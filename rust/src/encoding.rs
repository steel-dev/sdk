use serde_json::Value;

use crate::upload::Upload;

fn scalar(value: &Value) -> Option<String> {
    match value {
        Value::Null => None,
        Value::Bool(flag) => Some(flag.to_string()),
        Value::Number(number) => Some(number.to_string()),
        Value::String(text) => Some(text.clone()),
        Value::Array(_) | Value::Object(_) => None,
    }
}

fn flatten(key: &str, value: &Value, array_suffix: &str, out: &mut Vec<(String, String)>) {
    match value {
        Value::Null => {}
        Value::Bool(_) | Value::Number(_) | Value::String(_) => {
            if let Some(text) = scalar(value) {
                out.push((key.to_owned(), text));
            }
        }
        Value::Array(items) => {
            for item in items {
                flatten(&format!("{key}{array_suffix}"), item, array_suffix, out);
            }
        }
        Value::Object(entries) => {
            for (name, item) in entries {
                flatten(&format!("{key}[{name}]"), item, array_suffix, out);
            }
        }
    }
}

pub(crate) fn query(key: &str, value: &Value) -> Vec<(String, String)> {
    let mut out = Vec::new();
    flatten(key, value, "", &mut out);
    out
}

#[derive(Debug, Clone)]
pub(crate) enum FormField {
    Text(String, String),
    File(String, Upload),
}

pub(crate) fn form(key: &str, value: &Value) -> Vec<FormField> {
    let mut out = Vec::new();
    flatten(key, value, "[]", &mut out);
    out.into_iter()
        .map(|(name, text)| FormField::Text(name, text))
        .collect()
}

pub(crate) fn segment(value: &str) -> String {
    let mut out = String::new();
    for byte in value.bytes() {
        match byte {
            b'A'..=b'Z' | b'a'..=b'z' | b'0'..=b'9' | b'-' | b'.' | b'_' | b'~' => {
                out.push(char::from(byte));
            }
            other => out.push_str(&format!("%{other:02X}")),
        }
    }
    out
}

#[cfg(test)]
mod tests {
    use serde_json::json;

    use super::*;

    #[test]
    fn repeats_arrays_and_brackets_objects_in_queries() {
        assert_eq!(
            query(
                "filter",
                &json!({"tags": ["a", "b"], "limit": 2, "skip": null})
            ),
            vec![
                ("filter[limit]".to_owned(), "2".to_owned()),
                ("filter[tags]".to_owned(), "a".to_owned()),
                ("filter[tags]".to_owned(), "b".to_owned()),
            ]
        );
    }

    #[test]
    fn brackets_arrays_in_forms() {
        let fields = form("files", &json!(["a", "b"]));
        let names: Vec<String> = fields
            .into_iter()
            .map(|field| match field {
                FormField::Text(name, _) | FormField::File(name, _) => name,
            })
            .collect();
        assert_eq!(names, vec!["files[]", "files[]"]);
    }

    #[test]
    fn escapes_path_segments() {
        assert_eq!(segment("a b/c"), "a%20b%2Fc");
    }
}
