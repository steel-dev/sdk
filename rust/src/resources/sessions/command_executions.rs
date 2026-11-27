use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct SessionsCommandExecutions<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> SessionsCommandExecutions<'a> {
    /// Waits up to 20 seconds for an execution to finish and returns it. Use it when a run answered with state running.
    pub fn retrieve(
        &self,
        session_id: &str,
        execution_id: &str,
    ) -> crate::Call<'a, SessionCommandExecutionRetrieveResponse> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/commands/executions/{}",
            crate::encoding::segment(session_id),
            crate::encoding::segment(execution_id)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::GET, path);
            client.json(request).await
        })
    }

    /// Stops an execution. The running command stops before its next input, and commands that already ran are not undone.
    pub fn cancel(
        &self,
        session_id: &str,
        execution_id: &str,
    ) -> crate::Call<'a, SessionCommandExecutionCancelResponse> {
        let client = self.client;
        let path = format!(
            "/v1/sessions/{}/commands/executions/{}/cancel",
            crate::encoding::segment(session_id),
            crate::encoding::segment(execution_id)
        );
        crate::Call::new(async move {
            let request = crate::client::Request::new(reqwest::Method::POST, path);
            client.json(request).await
        })
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponse {
    /// The id of this execution. Use it to get or cancel the execution.
    pub id: String,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: SessionCommandExecutionRetrieveResponseEffect,
    /// The tab the execution used.
    #[serde(default)]
    pub page: Option<SessionCommandExecutionRetrieveResponsePage>,
    /// Commands started.
    pub ran: i64,
    /// This is the stored result of an earlier request. Nothing ran again.
    pub replayed: bool,
    pub results: Vec<crate::CommandResult>,
    pub state: SessionCommandExecutionRetrieveResponseState,
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
    /// Tabs with keys or a mouse button still held down. Left out when nothing is held.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub held: Option<Vec<crate::CommandHeld>>,
    /// Send it as after in the next request.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub next: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub observe: Option<SessionCommandExecutionRetrieveResponseObserve>,
    /// Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A failed command has its error in results instead.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub stopped: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionRetrieveResponseEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl SessionCommandExecutionRetrieveResponseEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for SessionCommandExecutionRetrieveResponseEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionRetrieveResponsePage {
    pub tab: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionRetrieveResponseState {
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
    #[serde(other)]
    Unknown,
}

impl SessionCommandExecutionRetrieveResponseState {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Running => "running",
            Self::Completed => "completed",
            Self::Failed => "failed",
            Self::Cancelled => "cancelled",
            Self::Timeout => "timeout",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandExecutionRetrieveResponseState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
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
    /// The id of this execution. Use it to get or cancel the execution.
    pub id: String,
    /// What happened to the page: nothing, a change, or a possible change.
    pub effect: SessionCommandExecutionCancelResponseEffect,
    /// The tab the execution used.
    #[serde(default)]
    pub page: Option<SessionCommandExecutionCancelResponsePage>,
    /// Commands started.
    pub ran: i64,
    /// This is the stored result of an earlier request. Nothing ran again.
    pub replayed: bool,
    pub results: Vec<crate::CommandResult>,
    pub state: SessionCommandExecutionCancelResponseState,
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
    /// Tabs with keys or a mouse button still held down. Left out when nothing is held.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub held: Option<Vec<crate::CommandHeld>>,
    /// Send it as after in the next request.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub next: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub observe: Option<SessionCommandExecutionCancelResponseObserve>,
    /// Why the execution stopped early, such as a cancel, a timeout, or a browser restart. A failed command has its error in results instead.
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub stopped: Option<crate::CommandErrorInfo>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionCancelResponseEffect {
    #[serde(rename = "none")]
    None,
    #[serde(rename = "applied")]
    Applied,
    #[serde(rename = "unknown")]
    Unknown,
    #[serde(other)]
    Other,
}

impl SessionCommandExecutionCancelResponseEffect {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::None => "none",
            Self::Applied => "applied",
            Self::Unknown => "unknown",
            Self::Other => "Other",
        }
    }
}

impl std::fmt::Display for SessionCommandExecutionCancelResponseEffect {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct SessionCommandExecutionCancelResponsePage {
    pub tab: String,
    pub title: String,
    pub url: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum SessionCommandExecutionCancelResponseState {
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
    #[serde(other)]
    Unknown,
}

impl SessionCommandExecutionCancelResponseState {
    pub fn as_str(&self) -> &'static str {
        match self {
            Self::Running => "running",
            Self::Completed => "completed",
            Self::Failed => "failed",
            Self::Cancelled => "cancelled",
            Self::Timeout => "timeout",
            Self::Unknown => "Unknown",
        }
    }
}

impl std::fmt::Display for SessionCommandExecutionCancelResponseState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.write_str(self.as_str())
    }
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
