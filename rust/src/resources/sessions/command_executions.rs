use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct SessionsCommandExecutions<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SessionsCommandExecutions<'a> {
    /// Waits up to 20 seconds for an execution to finish and returns it. Use it when a run answered with state running.
    pub async fn retrieve(
        &self,
        session_id: &str,
        execution_id: &str,
    ) -> Result<SessionCommandExecutionRetrieveResponse, crate::Error> {
        let request = crate::client::Request::new(
            reqwest::Method::GET,
            format!(
                "/v1/sessions/{}/commands/executions/{}",
                crate::encoding::segment(session_id),
                crate::encoding::segment(execution_id)
            ),
        );
        self.client.json(request).await
    }

    /// Stops an execution. The running command stops before its next input, and commands that already ran are not undone.
    pub async fn cancel(
        &self,
        session_id: &str,
        execution_id: &str,
    ) -> Result<SessionCommandExecutionCancelResponse, crate::Error> {
        let request = crate::client::Request::new(
            reqwest::Method::POST,
            format!(
                "/v1/sessions/{}/commands/executions/{}/cancel",
                crate::encoding::segment(session_id),
                crate::encoding::segment(execution_id)
            ),
        );
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponse {
    /// The id of this execution, the same as execution.id. Use it to get or cancel the execution.
    pub id: String,
    pub execution: SessionCommandExecutionRetrieveResponseExecution,
    /// The tab the execution used.
    #[serde(default)]
    pub page: Option<SessionCommandExecutionRetrieveResponsePage>,
    pub results: Vec<crate::CommandResult>,
    #[serde(
        rename = "bodyExpired",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub body_expired: Option<bool>,
    /// A dialog that is open on the tab and blocks it until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    /// Set when the execution ran with after. Whether another execution, a quick action, or a browser restart changed the session since then. Input from CDP clients or the live view does not count.
    #[serde(
        rename = "epochChanged",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub epoch_changed: Option<bool>,
    /// Send it as after in the next request.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub next: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub observe: Option<SessionCommandExecutionRetrieveResponseObserve>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponseExecution {
    pub id: String,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: SessionCommandExecutionRetrieveResponseExecutionEffect,
    /// Commands started.
    pub ran: i64,
    pub replayed: bool,
    pub state: SessionCommandExecutionRetrieveResponseExecutionState,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionRetrieveResponseExecutionEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionRetrieveResponseExecutionState {
    #[serde(rename = "queued")]
    Queued,
    #[serde(rename = "running")]
    Running,
    #[serde(rename = "completed")]
    Completed,
    #[serde(rename = "failed")]
    Failed,
    #[serde(rename = "cancelled")]
    Cancelled,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(rename = "expired")]
    Expired,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponsePage {
    pub tab: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponseObserve {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub additions: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub base: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub diff: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub full: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub removals: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub snapshot: Option<String>,
    #[serde(
        rename = "snapshotId",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub snapshot_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionCancelResponse {
    /// The id of this execution, the same as execution.id. Use it to get or cancel the execution.
    pub id: String,
    pub execution: SessionCommandExecutionCancelResponseExecution,
    /// The tab the execution used.
    #[serde(default)]
    pub page: Option<SessionCommandExecutionCancelResponsePage>,
    pub results: Vec<crate::CommandResult>,
    #[serde(
        rename = "bodyExpired",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub body_expired: Option<bool>,
    /// A dialog that is open on the tab and blocks it until it closes.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dialog: Option<crate::CommandDialog>,
    /// Set when the execution ran with after. Whether another execution, a quick action, or a browser restart changed the session since then. Input from CDP clients or the live view does not count.
    #[serde(
        rename = "epochChanged",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub epoch_changed: Option<bool>,
    /// Send it as after in the next request.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub next: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub observe: Option<SessionCommandExecutionCancelResponseObserve>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionCancelResponseExecution {
    pub id: String,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: SessionCommandExecutionCancelResponseExecutionEffect,
    /// Commands started.
    pub ran: i64,
    pub replayed: bool,
    pub state: SessionCommandExecutionCancelResponseExecutionState,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionCancelResponseExecutionEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionCancelResponseExecutionState {
    #[serde(rename = "queued")]
    Queued,
    #[serde(rename = "running")]
    Running,
    #[serde(rename = "completed")]
    Completed,
    #[serde(rename = "failed")]
    Failed,
    #[serde(rename = "cancelled")]
    Cancelled,
    #[serde(rename = "timeout")]
    Timeout,
    #[serde(rename = "expired")]
    Expired,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionCancelResponsePage {
    pub tab: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionCancelResponseObserve {
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub additions: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub base: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub diff: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub error: Option<crate::CommandErrorInfo>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub full: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub removals: Option<i64>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub snapshot: Option<String>,
    #[serde(
        rename = "snapshotId",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub snapshot_id: Option<String>,
}
