use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Projects<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Projects<'a> {
    /// Get a project by ID.
    pub async fn retrieve(&self, id: &str) -> Result<crate::Project, crate::Error> {
        let request = crate::client::Request::new(
            reqwest::Method::GET,
            format!("/v1/projects/{}", crate::encoding::segment(id)),
        );
        self.client.json(request).await
    }

    /// List projects for the authenticated organization.
    pub async fn list(&self) -> Result<crate::ProjectsList, crate::Error> {
        let request = crate::client::Request::new(reqwest::Method::GET, "/v1/projects".to_owned());
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct Project {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    #[serde(rename = "isDefault")]
    pub is_default: bool,
    #[serde(rename = "isProduction")]
    pub is_production: bool,
    pub name: String,
    #[serde(rename = "promotedToProductionAt", default)]
    pub promoted_to_production_at: Option<String>,
    pub slug: String,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProjectsList {
    pub projects: Vec<ProjectsListProject>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProjectsListProject {
    pub id: String,
    #[serde(rename = "createdAt")]
    pub created_at: String,
    #[serde(rename = "isDefault")]
    pub is_default: bool,
    #[serde(rename = "isProduction")]
    pub is_production: bool,
    pub name: String,
    #[serde(rename = "promotedToProductionAt", default)]
    pub promoted_to_production_at: Option<String>,
    pub slug: String,
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
}
