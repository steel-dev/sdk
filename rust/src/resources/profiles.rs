use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Copy)]
pub struct Profiles<'a> {
    pub(crate) client: &'a crate::Client,
}

impl<'a> Profiles<'a> {
    /// Create a new profile
    pub async fn create(
        &self,
        params: ProfileCreateParams,
    ) -> Result<ProfileCreateResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::POST, "/v1/profiles".to_owned());
        let mut fields = Vec::new();
        fields.push(crate::encoding::FormField::File(
            "userDataDir".to_owned(),
            params.user_data_dir.clone(),
        ));
        if let Some(value) = &params.dimensions {
            fields.extend(crate::encoding::form(
                "dimensions",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.project_id {
            fields.extend(crate::encoding::form(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.proxy_url {
            fields.extend(crate::encoding::form(
                "proxyUrl",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.use_proxy {
            fields.extend(crate::encoding::form(
                "useProxy",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.user_agent {
            fields.extend(crate::encoding::form(
                "userAgent",
                &serde_json::to_value(value)?,
            ));
        }
        request.body = crate::client::Body::Form(fields);
        self.client.json(request).await
    }

    /// Update an existing profile
    pub async fn update(
        &self,
        id: &str,
        params: ProfileUpdateParams,
    ) -> Result<ProfileUpdateResponse, crate::Error> {
        let mut request = crate::client::Request::new(
            reqwest::Method::PATCH,
            format!("/v1/profiles/{}", crate::encoding::segment(id)),
        );
        if let Some(value) = &params.query_project_id {
            request.query.extend(crate::encoding::query(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        let mut fields = Vec::new();
        fields.push(crate::encoding::FormField::File(
            "userDataDir".to_owned(),
            params.user_data_dir.clone(),
        ));
        if let Some(value) = &params.dimensions {
            fields.extend(crate::encoding::form(
                "dimensions",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.body_project_id {
            fields.extend(crate::encoding::form(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.proxy_url {
            fields.extend(crate::encoding::form(
                "proxyUrl",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.use_proxy {
            fields.extend(crate::encoding::form(
                "useProxy",
                &serde_json::to_value(value)?,
            ));
        }
        if let Some(value) = &params.user_agent {
            fields.extend(crate::encoding::form(
                "userAgent",
                &serde_json::to_value(value)?,
            ));
        }
        request.body = crate::client::Body::Form(fields);
        self.client.json(request).await
    }

    /// Retrieve a list of all profiles
    pub async fn list(
        &self,
        params: ProfileListParams,
    ) -> Result<ProfileListResponse, crate::Error> {
        let mut request =
            crate::client::Request::new(reqwest::Method::GET, "/v1/profiles".to_owned());
        if let Some(value) = &params.project_id {
            request.query.extend(crate::encoding::query(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        self.client.json(request).await
    }

    /// Retrieve a profile by ID
    pub async fn get(
        &self,
        id: &str,
        params: ProfileGetParams,
    ) -> Result<ProfileGetResponse, crate::Error> {
        let mut request = crate::client::Request::new(
            reqwest::Method::GET,
            format!("/v1/profiles/{}", crate::encoding::segment(id)),
        );
        if let Some(value) = &params.project_id {
            request.query.extend(crate::encoding::query(
                "projectId",
                &serde_json::to_value(value)?,
            ));
        }
        self.client.json(request).await
    }
}

#[derive(Debug, Clone, PartialEq)]
pub struct ProfileCreateParams {
    /// The user data directory associated with the profile
    pub user_data_dir: crate::Upload,
    /// The dimensions associated with the profile
    pub dimensions: Option<ProfileCreateParamsDimensions>,
    /// Project to create the profile in
    pub project_id: Option<String>,
    /// The proxy associated with the profile
    pub proxy_url: Option<String>,
    /// JSON-encoded proxy configuration associated with the profile. For fixed IPs, send {"type":"fixed","id":"fixed:<id>"}.
    pub use_proxy: Option<String>,
    /// The user agent associated with the profile
    pub user_agent: Option<String>,
}

impl ProfileCreateParams {
    pub fn new(user_data_dir: crate::Upload) -> Self {
        ProfileCreateParams {
            user_data_dir,
            dimensions: None,
            project_id: None,
            proxy_url: None,
            use_proxy: None,
            user_agent: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateParamsDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponse {
    /// The unique identifier for the profile
    pub id: String,
    /// The date and time when the profile was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// The credentials configuration associated with the profile
    #[serde(rename = "credentialsConfig")]
    pub credentials_config: serde_json::Value,
    /// The dimensions associated with the profile
    #[serde(default)]
    pub dimensions: Option<ProfileCreateResponseDimensions>,
    /// The extension IDs associated with the profile
    #[serde(rename = "extensionIds", default)]
    pub extension_ids: Option<Vec<String>>,
    /// The fingerprint associated with the profile
    #[serde(default)]
    pub fingerprint: Option<ProfileCreateResponseFingerprint>,
    /// The project ID associated with the profile
    #[serde(rename = "projectId", default)]
    pub project_id: Option<String>,
    /// The last session ID associated with the profile
    #[serde(rename = "sourceSessionId", default)]
    pub source_session_id: Option<String>,
    /// The status of the profile
    pub status: ProfileCreateResponseStatus,
    /// The date and time when the profile was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// The proxy configuration associated with the profile
    #[serde(rename = "useProxyConfig")]
    pub use_proxy_config: serde_json::Value,
    /// The user agent associated with the profile
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprint {
    pub fingerprint: ProfileCreateResponseFingerprintFingerprint,
    pub headers: ProfileCreateResponseFingerprintHeaders,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprint {
    #[serde(rename = "audioCodecs")]
    pub audio_codecs: std::collections::HashMap<String, String>,
    pub battery: ProfileCreateResponseFingerprintFingerprintBattery,
    pub fonts: Vec<String>,
    #[serde(rename = "mockWebRTC", default)]
    pub mock_web_rtc: Option<bool>,
    #[serde(rename = "multimediaDevices")]
    pub multimedia_devices: ProfileCreateResponseFingerprintFingerprintMultimediaDevices,
    pub navigator: ProfileCreateResponseFingerprintFingerprintNavigator,
    #[serde(rename = "pluginsData")]
    pub plugins_data: ProfileCreateResponseFingerprintFingerprintPluginsData,
    pub screen: ProfileCreateResponseFingerprintFingerprintScreen,
    #[serde(default)]
    pub slim: Option<bool>,
    #[serde(rename = "videoCard")]
    pub video_card: ProfileCreateResponseFingerprintFingerprintVideoCard,
    #[serde(rename = "videoCodecs")]
    pub video_codecs: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintBattery {
    #[serde(default)]
    pub charging: Option<bool>,
    #[serde(rename = "chargingTime", default)]
    pub charging_time: Option<f64>,
    #[serde(rename = "dischargingTime", default)]
    pub discharging_time: Option<f64>,
    #[serde(default)]
    pub level: Option<f64>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintMultimediaDevices {
    pub micros: Vec<ProfileCreateResponseFingerprintFingerprintMultimediaDevicesMicro>,
    pub speakers: Vec<ProfileCreateResponseFingerprintFingerprintMultimediaDevicesSpeaker>,
    pub webcams: Vec<ProfileCreateResponseFingerprintFingerprintMultimediaDevicesWebcam>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintMultimediaDevicesMicro {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintMultimediaDevicesSpeaker {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintMultimediaDevicesWebcam {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintNavigator {
    #[serde(rename = "appCodeName", default)]
    pub app_code_name: Option<String>,
    #[serde(rename = "appName", default)]
    pub app_name: Option<String>,
    #[serde(rename = "appVersion", default)]
    pub app_version: Option<String>,
    #[serde(rename = "deviceMemory", default)]
    pub device_memory: Option<f64>,
    #[serde(rename = "extraProperties")]
    pub extra_properties: ProfileCreateResponseFingerprintFingerprintNavigatorExtraProperties,
    #[serde(rename = "hardwareConcurrency", default)]
    pub hardware_concurrency: Option<f64>,
    #[serde(default)]
    pub language: Option<String>,
    pub languages: Vec<String>,
    #[serde(rename = "maxTouchPoints", default)]
    pub max_touch_points: Option<f64>,
    #[serde(default)]
    pub oscpu: Option<String>,
    #[serde(default)]
    pub platform: Option<String>,
    #[serde(default)]
    pub product: Option<String>,
    #[serde(rename = "productSub", default)]
    pub product_sub: Option<String>,
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
    #[serde(rename = "userAgentData")]
    pub user_agent_data: ProfileCreateResponseFingerprintFingerprintNavigatorUserAgentData,
    #[serde(default)]
    pub vendor: Option<String>,
    #[serde(rename = "vendorSub", default)]
    pub vendor_sub: Option<String>,
    #[serde(default)]
    pub webdriver: Option<bool>,
    #[serde(
        rename = "doNotTrack",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub do_not_track: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintNavigatorExtraProperties {
    #[serde(rename = "globalPrivacyControl", default)]
    pub global_privacy_control: Option<bool>,
    #[serde(rename = "installedApps")]
    pub installed_apps: Vec<String>,
    #[serde(rename = "pdfViewerEnabled", default)]
    pub pdf_viewer_enabled: Option<bool>,
    #[serde(rename = "vendorFlavors")]
    pub vendor_flavors: Vec<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintNavigatorUserAgentData {
    pub brands: Vec<ProfileCreateResponseFingerprintFingerprintNavigatorUserAgentDataBrand>,
    #[serde(default)]
    pub mobile: Option<bool>,
    #[serde(default)]
    pub platform: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintNavigatorUserAgentDataBrand {
    #[serde(default)]
    pub brand: Option<String>,
    #[serde(default)]
    pub version: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintPluginsData {
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<String>,
    pub plugins: Vec<ProfileCreateResponseFingerprintFingerprintPluginsDataPlugin>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintPluginsDataPlugin {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(default)]
    pub filename: Option<String>,
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<ProfileCreateResponseFingerprintFingerprintPluginsDataPluginMimeType>,
    #[serde(default)]
    pub name: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintPluginsDataPluginMimeType {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(rename = "enabledPlugin", default)]
    pub enabled_plugin: Option<String>,
    #[serde(default)]
    pub suffixes: Option<String>,
    #[serde(default)]
    pub r#type: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintScreen {
    #[serde(rename = "availHeight", default)]
    pub avail_height: Option<f64>,
    #[serde(rename = "availLeft", default)]
    pub avail_left: Option<f64>,
    #[serde(rename = "availTop", default)]
    pub avail_top: Option<f64>,
    #[serde(rename = "availWidth", default)]
    pub avail_width: Option<f64>,
    #[serde(rename = "clientHeight", default)]
    pub client_height: Option<f64>,
    #[serde(rename = "clientWidth", default)]
    pub client_width: Option<f64>,
    #[serde(rename = "colorDepth", default)]
    pub color_depth: Option<f64>,
    #[serde(rename = "devicePixelRatio", default)]
    pub device_pixel_ratio: Option<f64>,
    #[serde(rename = "hasHDR", default)]
    pub has_hdr: Option<bool>,
    #[serde(default)]
    pub height: Option<f64>,
    #[serde(rename = "innerHeight", default)]
    pub inner_height: Option<f64>,
    #[serde(rename = "innerWidth", default)]
    pub inner_width: Option<f64>,
    #[serde(rename = "outerHeight", default)]
    pub outer_height: Option<f64>,
    #[serde(rename = "outerWidth", default)]
    pub outer_width: Option<f64>,
    #[serde(rename = "pageXOffset", default)]
    pub page_x_offset: Option<f64>,
    #[serde(rename = "pageYOffset", default)]
    pub page_y_offset: Option<f64>,
    #[serde(rename = "pixelDepth", default)]
    pub pixel_depth: Option<f64>,
    #[serde(rename = "screenX", default)]
    pub screen_x: Option<f64>,
    #[serde(default)]
    pub width: Option<f64>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintFingerprintVideoCard {
    #[serde(default)]
    pub renderer: Option<String>,
    #[serde(default)]
    pub vendor: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileCreateResponseFingerprintHeaders {
    #[serde(rename = "user-agent", default)]
    pub user_agent: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub accept: Option<String>,
    #[serde(
        rename = "accept-encoding",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_encoding: Option<String>,
    #[serde(
        rename = "accept-language",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_language: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dnt: Option<String>,
    #[serde(rename = "sec-ch-ua", default, skip_serializing_if = "Option::is_none")]
    pub sec_ch_ua: Option<String>,
    #[serde(
        rename = "sec-ch-ua-mobile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_mobile: Option<String>,
    #[serde(
        rename = "sec-ch-ua-platform",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_platform: Option<String>,
    #[serde(
        rename = "sec-fetch-dest",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_dest: Option<String>,
    #[serde(
        rename = "sec-fetch-mode",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_mode: Option<String>,
    #[serde(
        rename = "sec-fetch-site",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_site: Option<String>,
    #[serde(
        rename = "sec-fetch-user",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_user: Option<String>,
    #[serde(
        rename = "upgrade-insecure-requests",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub upgrade_insecure_requests: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ProfileCreateResponseStatus {
    #[serde(rename = "UPLOADING")]
    Uploading,
    #[serde(rename = "READY")]
    Ready,
    #[serde(rename = "FAILED")]
    Failed,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, PartialEq)]
pub struct ProfileUpdateParams {
    /// The user data directory associated with the profile
    pub user_data_dir: crate::Upload,
    /// Project to query profiles from
    pub query_project_id: Option<String>,
    /// The dimensions associated with the profile
    pub dimensions: Option<ProfileUpdateParamsDimensions>,
    /// Project to create the profile in
    pub body_project_id: Option<String>,
    /// The proxy associated with the profile
    pub proxy_url: Option<String>,
    /// JSON-encoded proxy configuration associated with the profile. For fixed IPs, send {"type":"fixed","id":"fixed:<id>"}.
    pub use_proxy: Option<String>,
    /// The user agent associated with the profile
    pub user_agent: Option<String>,
}

impl ProfileUpdateParams {
    pub fn new(user_data_dir: crate::Upload) -> Self {
        ProfileUpdateParams {
            user_data_dir,
            query_project_id: None,
            dimensions: None,
            body_project_id: None,
            proxy_url: None,
            use_proxy: None,
            user_agent: None,
        }
    }
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateParamsDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponse {
    /// The unique identifier for the profile
    pub id: String,
    /// The date and time when the profile was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// The credentials configuration associated with the profile
    #[serde(rename = "credentialsConfig")]
    pub credentials_config: serde_json::Value,
    /// The dimensions associated with the profile
    #[serde(default)]
    pub dimensions: Option<ProfileUpdateResponseDimensions>,
    /// The extension IDs associated with the profile
    #[serde(rename = "extensionIds", default)]
    pub extension_ids: Option<Vec<String>>,
    /// The fingerprint associated with the profile
    #[serde(default)]
    pub fingerprint: Option<ProfileUpdateResponseFingerprint>,
    /// The project ID associated with the profile
    #[serde(rename = "projectId", default)]
    pub project_id: Option<String>,
    /// The last session ID associated with the profile
    #[serde(rename = "sourceSessionId", default)]
    pub source_session_id: Option<String>,
    /// The status of the profile
    pub status: ProfileUpdateResponseStatus,
    /// The date and time when the profile was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// The proxy configuration associated with the profile
    #[serde(rename = "useProxyConfig")]
    pub use_proxy_config: serde_json::Value,
    /// The user agent associated with the profile
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprint {
    pub fingerprint: ProfileUpdateResponseFingerprintFingerprint,
    pub headers: ProfileUpdateResponseFingerprintHeaders,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprint {
    #[serde(rename = "audioCodecs")]
    pub audio_codecs: std::collections::HashMap<String, String>,
    pub battery: ProfileUpdateResponseFingerprintFingerprintBattery,
    pub fonts: Vec<String>,
    #[serde(rename = "mockWebRTC", default)]
    pub mock_web_rtc: Option<bool>,
    #[serde(rename = "multimediaDevices")]
    pub multimedia_devices: ProfileUpdateResponseFingerprintFingerprintMultimediaDevices,
    pub navigator: ProfileUpdateResponseFingerprintFingerprintNavigator,
    #[serde(rename = "pluginsData")]
    pub plugins_data: ProfileUpdateResponseFingerprintFingerprintPluginsData,
    pub screen: ProfileUpdateResponseFingerprintFingerprintScreen,
    #[serde(default)]
    pub slim: Option<bool>,
    #[serde(rename = "videoCard")]
    pub video_card: ProfileUpdateResponseFingerprintFingerprintVideoCard,
    #[serde(rename = "videoCodecs")]
    pub video_codecs: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintBattery {
    #[serde(default)]
    pub charging: Option<bool>,
    #[serde(rename = "chargingTime", default)]
    pub charging_time: Option<f64>,
    #[serde(rename = "dischargingTime", default)]
    pub discharging_time: Option<f64>,
    #[serde(default)]
    pub level: Option<f64>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintMultimediaDevices {
    pub micros: Vec<ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro>,
    pub speakers: Vec<ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker>,
    pub webcams: Vec<ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintNavigator {
    #[serde(rename = "appCodeName", default)]
    pub app_code_name: Option<String>,
    #[serde(rename = "appName", default)]
    pub app_name: Option<String>,
    #[serde(rename = "appVersion", default)]
    pub app_version: Option<String>,
    #[serde(rename = "deviceMemory", default)]
    pub device_memory: Option<f64>,
    #[serde(rename = "extraProperties")]
    pub extra_properties: ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties,
    #[serde(rename = "hardwareConcurrency", default)]
    pub hardware_concurrency: Option<f64>,
    #[serde(default)]
    pub language: Option<String>,
    pub languages: Vec<String>,
    #[serde(rename = "maxTouchPoints", default)]
    pub max_touch_points: Option<f64>,
    #[serde(default)]
    pub oscpu: Option<String>,
    #[serde(default)]
    pub platform: Option<String>,
    #[serde(default)]
    pub product: Option<String>,
    #[serde(rename = "productSub", default)]
    pub product_sub: Option<String>,
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
    #[serde(rename = "userAgentData")]
    pub user_agent_data: ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData,
    #[serde(default)]
    pub vendor: Option<String>,
    #[serde(rename = "vendorSub", default)]
    pub vendor_sub: Option<String>,
    #[serde(default)]
    pub webdriver: Option<bool>,
    #[serde(
        rename = "doNotTrack",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub do_not_track: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties {
    #[serde(rename = "globalPrivacyControl", default)]
    pub global_privacy_control: Option<bool>,
    #[serde(rename = "installedApps")]
    pub installed_apps: Vec<String>,
    #[serde(rename = "pdfViewerEnabled", default)]
    pub pdf_viewer_enabled: Option<bool>,
    #[serde(rename = "vendorFlavors")]
    pub vendor_flavors: Vec<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData {
    pub brands: Vec<ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand>,
    #[serde(default)]
    pub mobile: Option<bool>,
    #[serde(default)]
    pub platform: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand {
    #[serde(default)]
    pub brand: Option<String>,
    #[serde(default)]
    pub version: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintPluginsData {
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<String>,
    pub plugins: Vec<ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(default)]
    pub filename: Option<String>,
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType>,
    #[serde(default)]
    pub name: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(rename = "enabledPlugin", default)]
    pub enabled_plugin: Option<String>,
    #[serde(default)]
    pub suffixes: Option<String>,
    #[serde(default)]
    pub r#type: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintScreen {
    #[serde(rename = "availHeight", default)]
    pub avail_height: Option<f64>,
    #[serde(rename = "availLeft", default)]
    pub avail_left: Option<f64>,
    #[serde(rename = "availTop", default)]
    pub avail_top: Option<f64>,
    #[serde(rename = "availWidth", default)]
    pub avail_width: Option<f64>,
    #[serde(rename = "clientHeight", default)]
    pub client_height: Option<f64>,
    #[serde(rename = "clientWidth", default)]
    pub client_width: Option<f64>,
    #[serde(rename = "colorDepth", default)]
    pub color_depth: Option<f64>,
    #[serde(rename = "devicePixelRatio", default)]
    pub device_pixel_ratio: Option<f64>,
    #[serde(rename = "hasHDR", default)]
    pub has_hdr: Option<bool>,
    #[serde(default)]
    pub height: Option<f64>,
    #[serde(rename = "innerHeight", default)]
    pub inner_height: Option<f64>,
    #[serde(rename = "innerWidth", default)]
    pub inner_width: Option<f64>,
    #[serde(rename = "outerHeight", default)]
    pub outer_height: Option<f64>,
    #[serde(rename = "outerWidth", default)]
    pub outer_width: Option<f64>,
    #[serde(rename = "pageXOffset", default)]
    pub page_x_offset: Option<f64>,
    #[serde(rename = "pageYOffset", default)]
    pub page_y_offset: Option<f64>,
    #[serde(rename = "pixelDepth", default)]
    pub pixel_depth: Option<f64>,
    #[serde(rename = "screenX", default)]
    pub screen_x: Option<f64>,
    #[serde(default)]
    pub width: Option<f64>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintFingerprintVideoCard {
    #[serde(default)]
    pub renderer: Option<String>,
    #[serde(default)]
    pub vendor: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileUpdateResponseFingerprintHeaders {
    #[serde(rename = "user-agent", default)]
    pub user_agent: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub accept: Option<String>,
    #[serde(
        rename = "accept-encoding",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_encoding: Option<String>,
    #[serde(
        rename = "accept-language",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_language: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dnt: Option<String>,
    #[serde(rename = "sec-ch-ua", default, skip_serializing_if = "Option::is_none")]
    pub sec_ch_ua: Option<String>,
    #[serde(
        rename = "sec-ch-ua-mobile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_mobile: Option<String>,
    #[serde(
        rename = "sec-ch-ua-platform",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_platform: Option<String>,
    #[serde(
        rename = "sec-fetch-dest",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_dest: Option<String>,
    #[serde(
        rename = "sec-fetch-mode",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_mode: Option<String>,
    #[serde(
        rename = "sec-fetch-site",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_site: Option<String>,
    #[serde(
        rename = "sec-fetch-user",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_user: Option<String>,
    #[serde(
        rename = "upgrade-insecure-requests",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub upgrade_insecure_requests: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ProfileUpdateResponseStatus {
    #[serde(rename = "UPLOADING")]
    Uploading,
    #[serde(rename = "READY")]
    Ready,
    #[serde(rename = "FAILED")]
    Failed,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct ProfileListParams {
    /// Project to query profiles from
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponse {
    /// The total number of profiles
    pub count: f64,
    /// The list of profiles
    pub profiles: Vec<ProfileListResponseProfile>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfile {
    /// The unique identifier for the profile
    pub id: String,
    /// The date and time when the profile was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// The credentials configuration associated with the profile
    #[serde(rename = "credentialsConfig")]
    pub credentials_config: serde_json::Value,
    /// The dimensions associated with the profile
    #[serde(default)]
    pub dimensions: Option<ProfileListResponseProfileDimensions>,
    /// The extension IDs associated with the profile
    #[serde(rename = "extensionIds", default)]
    pub extension_ids: Option<Vec<String>>,
    /// The fingerprint associated with the profile
    #[serde(default)]
    pub fingerprint: Option<ProfileListResponseProfileFingerprint>,
    /// The project ID associated with the profile
    #[serde(rename = "projectId", default)]
    pub project_id: Option<String>,
    /// The last session ID associated with the profile
    #[serde(rename = "sourceSessionId", default)]
    pub source_session_id: Option<String>,
    /// The status of the profile
    pub status: ProfileListResponseProfileStatus,
    /// The date and time when the profile was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// The proxy configuration associated with the profile
    #[serde(rename = "useProxyConfig")]
    pub use_proxy_config: serde_json::Value,
    /// The user agent associated with the profile
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprint {
    pub fingerprint: ProfileListResponseProfileFingerprintFingerprint,
    pub headers: ProfileListResponseProfileFingerprintHeaders,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprint {
    #[serde(rename = "audioCodecs")]
    pub audio_codecs: std::collections::HashMap<String, String>,
    pub battery: ProfileListResponseProfileFingerprintFingerprintBattery,
    pub fonts: Vec<String>,
    #[serde(rename = "mockWebRTC", default)]
    pub mock_web_rtc: Option<bool>,
    #[serde(rename = "multimediaDevices")]
    pub multimedia_devices: ProfileListResponseProfileFingerprintFingerprintMultimediaDevices,
    pub navigator: ProfileListResponseProfileFingerprintFingerprintNavigator,
    #[serde(rename = "pluginsData")]
    pub plugins_data: ProfileListResponseProfileFingerprintFingerprintPluginsData,
    pub screen: ProfileListResponseProfileFingerprintFingerprintScreen,
    #[serde(default)]
    pub slim: Option<bool>,
    #[serde(rename = "videoCard")]
    pub video_card: ProfileListResponseProfileFingerprintFingerprintVideoCard,
    #[serde(rename = "videoCodecs")]
    pub video_codecs: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintBattery {
    #[serde(default)]
    pub charging: Option<bool>,
    #[serde(rename = "chargingTime", default)]
    pub charging_time: Option<f64>,
    #[serde(rename = "dischargingTime", default)]
    pub discharging_time: Option<f64>,
    #[serde(default)]
    pub level: Option<f64>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintMultimediaDevices {
    pub micros: Vec<ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro>,
    pub speakers: Vec<ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker>,
    pub webcams: Vec<ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintNavigator {
    #[serde(rename = "appCodeName", default)]
    pub app_code_name: Option<String>,
    #[serde(rename = "appName", default)]
    pub app_name: Option<String>,
    #[serde(rename = "appVersion", default)]
    pub app_version: Option<String>,
    #[serde(rename = "deviceMemory", default)]
    pub device_memory: Option<f64>,
    #[serde(rename = "extraProperties")]
    pub extra_properties: ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties,
    #[serde(rename = "hardwareConcurrency", default)]
    pub hardware_concurrency: Option<f64>,
    #[serde(default)]
    pub language: Option<String>,
    pub languages: Vec<String>,
    #[serde(rename = "maxTouchPoints", default)]
    pub max_touch_points: Option<f64>,
    #[serde(default)]
    pub oscpu: Option<String>,
    #[serde(default)]
    pub platform: Option<String>,
    #[serde(default)]
    pub product: Option<String>,
    #[serde(rename = "productSub", default)]
    pub product_sub: Option<String>,
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
    #[serde(rename = "userAgentData")]
    pub user_agent_data: ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData,
    #[serde(default)]
    pub vendor: Option<String>,
    #[serde(rename = "vendorSub", default)]
    pub vendor_sub: Option<String>,
    #[serde(default)]
    pub webdriver: Option<bool>,
    #[serde(
        rename = "doNotTrack",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub do_not_track: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties {
    #[serde(rename = "globalPrivacyControl", default)]
    pub global_privacy_control: Option<bool>,
    #[serde(rename = "installedApps")]
    pub installed_apps: Vec<String>,
    #[serde(rename = "pdfViewerEnabled", default)]
    pub pdf_viewer_enabled: Option<bool>,
    #[serde(rename = "vendorFlavors")]
    pub vendor_flavors: Vec<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData {
    pub brands: Vec<ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand>,
    #[serde(default)]
    pub mobile: Option<bool>,
    #[serde(default)]
    pub platform: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand {
    #[serde(default)]
    pub brand: Option<String>,
    #[serde(default)]
    pub version: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintPluginsData {
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<String>,
    pub plugins: Vec<ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(default)]
    pub filename: Option<String>,
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType>,
    #[serde(default)]
    pub name: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(rename = "enabledPlugin", default)]
    pub enabled_plugin: Option<String>,
    #[serde(default)]
    pub suffixes: Option<String>,
    #[serde(default)]
    pub r#type: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintScreen {
    #[serde(rename = "availHeight", default)]
    pub avail_height: Option<f64>,
    #[serde(rename = "availLeft", default)]
    pub avail_left: Option<f64>,
    #[serde(rename = "availTop", default)]
    pub avail_top: Option<f64>,
    #[serde(rename = "availWidth", default)]
    pub avail_width: Option<f64>,
    #[serde(rename = "clientHeight", default)]
    pub client_height: Option<f64>,
    #[serde(rename = "clientWidth", default)]
    pub client_width: Option<f64>,
    #[serde(rename = "colorDepth", default)]
    pub color_depth: Option<f64>,
    #[serde(rename = "devicePixelRatio", default)]
    pub device_pixel_ratio: Option<f64>,
    #[serde(rename = "hasHDR", default)]
    pub has_hdr: Option<bool>,
    #[serde(default)]
    pub height: Option<f64>,
    #[serde(rename = "innerHeight", default)]
    pub inner_height: Option<f64>,
    #[serde(rename = "innerWidth", default)]
    pub inner_width: Option<f64>,
    #[serde(rename = "outerHeight", default)]
    pub outer_height: Option<f64>,
    #[serde(rename = "outerWidth", default)]
    pub outer_width: Option<f64>,
    #[serde(rename = "pageXOffset", default)]
    pub page_x_offset: Option<f64>,
    #[serde(rename = "pageYOffset", default)]
    pub page_y_offset: Option<f64>,
    #[serde(rename = "pixelDepth", default)]
    pub pixel_depth: Option<f64>,
    #[serde(rename = "screenX", default)]
    pub screen_x: Option<f64>,
    #[serde(default)]
    pub width: Option<f64>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintFingerprintVideoCard {
    #[serde(default)]
    pub renderer: Option<String>,
    #[serde(default)]
    pub vendor: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileListResponseProfileFingerprintHeaders {
    #[serde(rename = "user-agent", default)]
    pub user_agent: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub accept: Option<String>,
    #[serde(
        rename = "accept-encoding",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_encoding: Option<String>,
    #[serde(
        rename = "accept-language",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_language: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dnt: Option<String>,
    #[serde(rename = "sec-ch-ua", default, skip_serializing_if = "Option::is_none")]
    pub sec_ch_ua: Option<String>,
    #[serde(
        rename = "sec-ch-ua-mobile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_mobile: Option<String>,
    #[serde(
        rename = "sec-ch-ua-platform",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_platform: Option<String>,
    #[serde(
        rename = "sec-fetch-dest",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_dest: Option<String>,
    #[serde(
        rename = "sec-fetch-mode",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_mode: Option<String>,
    #[serde(
        rename = "sec-fetch-site",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_site: Option<String>,
    #[serde(
        rename = "sec-fetch-user",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_user: Option<String>,
    #[serde(
        rename = "upgrade-insecure-requests",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub upgrade_insecure_requests: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ProfileListResponseProfileStatus {
    #[serde(rename = "UPLOADING")]
    Uploading,
    #[serde(rename = "READY")]
    Ready,
    #[serde(rename = "FAILED")]
    Failed,
    #[serde(other)]
    Unknown,
}

#[derive(Debug, Clone, Default, PartialEq)]
pub struct ProfileGetParams {
    /// Project to query profiles from
    pub project_id: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponse {
    /// The unique identifier for the profile
    pub id: String,
    /// The date and time when the profile was created
    #[serde(rename = "createdAt")]
    pub created_at: String,
    /// The credentials configuration associated with the profile
    #[serde(rename = "credentialsConfig")]
    pub credentials_config: serde_json::Value,
    /// The dimensions associated with the profile
    #[serde(default)]
    pub dimensions: Option<ProfileGetResponseDimensions>,
    /// The extension IDs associated with the profile
    #[serde(rename = "extensionIds", default)]
    pub extension_ids: Option<Vec<String>>,
    /// The fingerprint associated with the profile
    #[serde(default)]
    pub fingerprint: Option<ProfileGetResponseFingerprint>,
    /// The project ID associated with the profile
    #[serde(rename = "projectId", default)]
    pub project_id: Option<String>,
    /// The last session ID associated with the profile
    #[serde(rename = "sourceSessionId", default)]
    pub source_session_id: Option<String>,
    /// The status of the profile
    pub status: ProfileGetResponseStatus,
    /// The date and time when the profile was last updated
    #[serde(rename = "updatedAt")]
    pub updated_at: String,
    /// The proxy configuration associated with the profile
    #[serde(rename = "useProxyConfig")]
    pub use_proxy_config: serde_json::Value,
    /// The user agent associated with the profile
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseDimensions {
    pub height: f64,
    pub width: f64,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprint {
    pub fingerprint: ProfileGetResponseFingerprintFingerprint,
    pub headers: ProfileGetResponseFingerprintHeaders,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprint {
    #[serde(rename = "audioCodecs")]
    pub audio_codecs: std::collections::HashMap<String, String>,
    pub battery: ProfileGetResponseFingerprintFingerprintBattery,
    pub fonts: Vec<String>,
    #[serde(rename = "mockWebRTC", default)]
    pub mock_web_rtc: Option<bool>,
    #[serde(rename = "multimediaDevices")]
    pub multimedia_devices: ProfileGetResponseFingerprintFingerprintMultimediaDevices,
    pub navigator: ProfileGetResponseFingerprintFingerprintNavigator,
    #[serde(rename = "pluginsData")]
    pub plugins_data: ProfileGetResponseFingerprintFingerprintPluginsData,
    pub screen: ProfileGetResponseFingerprintFingerprintScreen,
    #[serde(default)]
    pub slim: Option<bool>,
    #[serde(rename = "videoCard")]
    pub video_card: ProfileGetResponseFingerprintFingerprintVideoCard,
    #[serde(rename = "videoCodecs")]
    pub video_codecs: std::collections::HashMap<String, String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintBattery {
    #[serde(default)]
    pub charging: Option<bool>,
    #[serde(rename = "chargingTime", default)]
    pub charging_time: Option<f64>,
    #[serde(rename = "dischargingTime", default)]
    pub discharging_time: Option<f64>,
    #[serde(default)]
    pub level: Option<f64>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintMultimediaDevices {
    pub micros: Vec<ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro>,
    pub speakers: Vec<ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker>,
    pub webcams: Vec<ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam {
    #[serde(rename = "deviceId", default)]
    pub device_id: Option<String>,
    #[serde(rename = "groupId", default)]
    pub group_id: Option<String>,
    #[serde(default)]
    pub kind: Option<String>,
    #[serde(default)]
    pub label: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintNavigator {
    #[serde(rename = "appCodeName", default)]
    pub app_code_name: Option<String>,
    #[serde(rename = "appName", default)]
    pub app_name: Option<String>,
    #[serde(rename = "appVersion", default)]
    pub app_version: Option<String>,
    #[serde(rename = "deviceMemory", default)]
    pub device_memory: Option<f64>,
    #[serde(rename = "extraProperties")]
    pub extra_properties: ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties,
    #[serde(rename = "hardwareConcurrency", default)]
    pub hardware_concurrency: Option<f64>,
    #[serde(default)]
    pub language: Option<String>,
    pub languages: Vec<String>,
    #[serde(rename = "maxTouchPoints", default)]
    pub max_touch_points: Option<f64>,
    #[serde(default)]
    pub oscpu: Option<String>,
    #[serde(default)]
    pub platform: Option<String>,
    #[serde(default)]
    pub product: Option<String>,
    #[serde(rename = "productSub", default)]
    pub product_sub: Option<String>,
    #[serde(rename = "userAgent", default)]
    pub user_agent: Option<String>,
    #[serde(rename = "userAgentData")]
    pub user_agent_data: ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData,
    #[serde(default)]
    pub vendor: Option<String>,
    #[serde(rename = "vendorSub", default)]
    pub vendor_sub: Option<String>,
    #[serde(default)]
    pub webdriver: Option<bool>,
    #[serde(
        rename = "doNotTrack",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub do_not_track: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties {
    #[serde(rename = "globalPrivacyControl", default)]
    pub global_privacy_control: Option<bool>,
    #[serde(rename = "installedApps")]
    pub installed_apps: Vec<String>,
    #[serde(rename = "pdfViewerEnabled", default)]
    pub pdf_viewer_enabled: Option<bool>,
    #[serde(rename = "vendorFlavors")]
    pub vendor_flavors: Vec<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData {
    pub brands: Vec<ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand>,
    #[serde(default)]
    pub mobile: Option<bool>,
    #[serde(default)]
    pub platform: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand {
    #[serde(default)]
    pub brand: Option<String>,
    #[serde(default)]
    pub version: Option<String>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintPluginsData {
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<String>,
    pub plugins: Vec<ProfileGetResponseFingerprintFingerprintPluginsDataPlugin>,
}

#[derive(Debug, Clone, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintPluginsDataPlugin {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(default)]
    pub filename: Option<String>,
    #[serde(rename = "mimeTypes")]
    pub mime_types: Vec<ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType>,
    #[serde(default)]
    pub name: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType {
    #[serde(default)]
    pub description: Option<String>,
    #[serde(rename = "enabledPlugin", default)]
    pub enabled_plugin: Option<String>,
    #[serde(default)]
    pub suffixes: Option<String>,
    #[serde(default)]
    pub r#type: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintScreen {
    #[serde(rename = "availHeight", default)]
    pub avail_height: Option<f64>,
    #[serde(rename = "availLeft", default)]
    pub avail_left: Option<f64>,
    #[serde(rename = "availTop", default)]
    pub avail_top: Option<f64>,
    #[serde(rename = "availWidth", default)]
    pub avail_width: Option<f64>,
    #[serde(rename = "clientHeight", default)]
    pub client_height: Option<f64>,
    #[serde(rename = "clientWidth", default)]
    pub client_width: Option<f64>,
    #[serde(rename = "colorDepth", default)]
    pub color_depth: Option<f64>,
    #[serde(rename = "devicePixelRatio", default)]
    pub device_pixel_ratio: Option<f64>,
    #[serde(rename = "hasHDR", default)]
    pub has_hdr: Option<bool>,
    #[serde(default)]
    pub height: Option<f64>,
    #[serde(rename = "innerHeight", default)]
    pub inner_height: Option<f64>,
    #[serde(rename = "innerWidth", default)]
    pub inner_width: Option<f64>,
    #[serde(rename = "outerHeight", default)]
    pub outer_height: Option<f64>,
    #[serde(rename = "outerWidth", default)]
    pub outer_width: Option<f64>,
    #[serde(rename = "pageXOffset", default)]
    pub page_x_offset: Option<f64>,
    #[serde(rename = "pageYOffset", default)]
    pub page_y_offset: Option<f64>,
    #[serde(rename = "pixelDepth", default)]
    pub pixel_depth: Option<f64>,
    #[serde(rename = "screenX", default)]
    pub screen_x: Option<f64>,
    #[serde(default)]
    pub width: Option<f64>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintFingerprintVideoCard {
    #[serde(default)]
    pub renderer: Option<String>,
    #[serde(default)]
    pub vendor: Option<String>,
}

#[derive(Debug, Clone, Default, PartialEq, Serialize, Deserialize)]
pub struct ProfileGetResponseFingerprintHeaders {
    #[serde(rename = "user-agent", default)]
    pub user_agent: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub accept: Option<String>,
    #[serde(
        rename = "accept-encoding",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_encoding: Option<String>,
    #[serde(
        rename = "accept-language",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub accept_language: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub dnt: Option<String>,
    #[serde(rename = "sec-ch-ua", default, skip_serializing_if = "Option::is_none")]
    pub sec_ch_ua: Option<String>,
    #[serde(
        rename = "sec-ch-ua-mobile",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_mobile: Option<String>,
    #[serde(
        rename = "sec-ch-ua-platform",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_ch_ua_platform: Option<String>,
    #[serde(
        rename = "sec-fetch-dest",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_dest: Option<String>,
    #[serde(
        rename = "sec-fetch-mode",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_mode: Option<String>,
    #[serde(
        rename = "sec-fetch-site",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_site: Option<String>,
    #[serde(
        rename = "sec-fetch-user",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub sec_fetch_user: Option<String>,
    #[serde(
        rename = "upgrade-insecure-requests",
        default,
        skip_serializing_if = "Option::is_none"
    )]
    pub upgrade_insecure_requests: Option<String>,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash, Serialize, Deserialize)]
pub enum ProfileGetResponseStatus {
    #[serde(rename = "UPLOADING")]
    Uploading,
    #[serde(rename = "READY")]
    Ready,
    #[serde(rename = "FAILED")]
    Failed,
    #[serde(other)]
    Unknown,
}
