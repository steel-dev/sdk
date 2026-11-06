// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package steel

import (
	"bytes"
	"context"
	"errors"
	"fmt"
	"io"
	"mime/multipart"
	"net/http"
	"net/url"
	"slices"
	"time"

	"github.com/steel-dev/sdk/go/internal/apiform"
	"github.com/steel-dev/sdk/go/internal/apijson"
	"github.com/steel-dev/sdk/go/internal/apiquery"
	"github.com/steel-dev/sdk/go/internal/param"
	"github.com/steel-dev/sdk/go/internal/requestconfig"
	"github.com/steel-dev/sdk/go/option"
)

// ProfileService contains methods and other services that help with interacting
// with the steel API.
//
// Note, unlike clients, this service does not read variables from the
// environment automatically. You should not instantiate this service directly,
// and instead use the [NewProfileService] method instead.
type ProfileService struct {
	Options []option.RequestOption
}

// NewProfileService generates a new service that applies the given options to
// each request. These options are applied after the parent client's options (if
// there is one), and before any request-specific options.
func NewProfileService(opts ...option.RequestOption) (r *ProfileService) {
	r = &ProfileService{}
	r.Options = opts
	return
}

// Create a new profile
func (r *ProfileService) New(ctx context.Context, body ProfileNewParams, opts ...option.RequestOption) (res *ProfileNewResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/profiles"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPost, path, body, &res, opts...)
	return res, err
}

type ProfileNewParams struct {
	// The user data directory associated with the profile
	UserDataDir param.Field[io.Reader] `json:"userDataDir" api:"required" format:"binary"`
	// The dimensions associated with the profile
	Dimensions param.Field[ProfileNewParamsDimensions] `json:"dimensions"`
	// Project to create the profile in
	ProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// The proxy associated with the profile
	ProxyURL param.Field[string] `json:"proxyUrl"`
	// JSON-encoded proxy configuration associated with the profile. For fixed IPs,
	// send {"type":"fixed","id":"fixed:<id>"}.
	UseProxy param.Field[string] `json:"useProxy"`
	// The user agent associated with the profile
	UserAgent param.Field[string] `json:"userAgent"`
}

func (r ProfileNewParams) MarshalMultipart() (data []byte, contentType string, err error) {
	buf := bytes.NewBuffer(nil)
	writer := multipart.NewWriter(buf)
	err = apiform.MarshalRoot(r, writer)
	if err != nil {
		writer.Close()
		return nil, "", err
	}
	err = writer.Close()
	if err != nil {
		return nil, "", err
	}
	return buf.Bytes(), writer.FormDataContentType(), nil
}

// The dimensions associated with the profile
type ProfileNewParamsDimensions struct {
	Height param.Field[float64] `json:"height" api:"required"`
	Width  param.Field[float64] `json:"width" api:"required"`
}

func (r ProfileNewParamsDimensions) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type ProfileNewResponse struct {
	// The unique identifier for the profile
	ID string `json:"id" api:"required" format:"uuid"`
	// The date and time when the profile was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// The credentials configuration associated with the profile
	CredentialsConfig interface{} `json:"credentialsConfig" api:"required"`
	// The dimensions associated with the profile
	Dimensions ProfileNewResponseDimensions `json:"dimensions" api:"required,nullable"`
	// The extension IDs associated with the profile
	ExtensionIDs []string `json:"extensionIds" api:"required,nullable"`
	// The fingerprint associated with the profile
	Fingerprint ProfileNewResponseFingerprint `json:"fingerprint" api:"required,nullable"`
	// The project ID associated with the profile
	ProjectID string `json:"projectId" api:"required,nullable" format:"uuid"`
	// The last session ID associated with the profile
	SourceSessionID string `json:"sourceSessionId" api:"required,nullable" format:"uuid"`
	// The status of the profile
	Status ProfileNewResponseStatus `json:"status" api:"required"`
	// The date and time when the profile was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// The proxy configuration associated with the profile
	UseProxyConfig interface{} `json:"useProxyConfig" api:"required"`
	// The user agent associated with the profile
	UserAgent string                 `json:"userAgent" api:"required,nullable"`
	JSON      profileNewResponseJSON `json:"-"`
}

// profileNewResponseJSON contains the JSON metadata for the struct
// [ProfileNewResponse]
type profileNewResponseJSON struct {
	ID                apijson.Field
	CreatedAt         apijson.Field
	CredentialsConfig apijson.Field
	Dimensions        apijson.Field
	ExtensionIDs      apijson.Field
	Fingerprint       apijson.Field
	ProjectID         apijson.Field
	SourceSessionID   apijson.Field
	Status            apijson.Field
	UpdatedAt         apijson.Field
	UseProxyConfig    apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileNewResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseJSON) RawJSON() string {
	return r.raw
}

// The dimensions associated with the profile
type ProfileNewResponseDimensions struct {
	Height float64                          `json:"height" api:"required"`
	Width  float64                          `json:"width" api:"required"`
	JSON   profileNewResponseDimensionsJSON `json:"-"`
}

// profileNewResponseDimensionsJSON contains the JSON metadata for the struct
// [ProfileNewResponseDimensions]
type profileNewResponseDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseDimensionsJSON) RawJSON() string {
	return r.raw
}

// The fingerprint associated with the profile
type ProfileNewResponseFingerprint struct {
	Fingerprint ProfileNewResponseFingerprintFingerprint `json:"fingerprint" api:"required"`
	Headers     ProfileNewResponseFingerprintHeaders     `json:"headers" api:"required"`
	JSON        profileNewResponseFingerprintJSON        `json:"-"`
}

// profileNewResponseFingerprintJSON contains the JSON metadata for the struct
// [ProfileNewResponseFingerprint]
type profileNewResponseFingerprintJSON struct {
	Fingerprint apijson.Field
	Headers     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprint struct {
	AudioCodecs       map[string]string                                         `json:"audioCodecs" api:"required"`
	Battery           ProfileNewResponseFingerprintFingerprintBattery           `json:"battery" api:"required"`
	Fonts             []string                                                  `json:"fonts" api:"required"`
	MockWebRtc        bool                                                      `json:"mockWebRTC" api:"required,nullable"`
	MultimediaDevices ProfileNewResponseFingerprintFingerprintMultimediaDevices `json:"multimediaDevices" api:"required"`
	Navigator         ProfileNewResponseFingerprintFingerprintNavigator         `json:"navigator" api:"required"`
	PluginsData       ProfileNewResponseFingerprintFingerprintPluginsData       `json:"pluginsData" api:"required"`
	Screen            ProfileNewResponseFingerprintFingerprintScreen            `json:"screen" api:"required"`
	Slim              bool                                                      `json:"slim" api:"required,nullable"`
	VideoCard         ProfileNewResponseFingerprintFingerprintVideoCard         `json:"videoCard" api:"required"`
	VideoCodecs       map[string]string                                         `json:"videoCodecs" api:"required"`
	JSON              profileNewResponseFingerprintFingerprintJSON              `json:"-"`
}

// profileNewResponseFingerprintFingerprintJSON contains the JSON metadata for
// the struct [ProfileNewResponseFingerprintFingerprint]
type profileNewResponseFingerprintFingerprintJSON struct {
	AudioCodecs       apijson.Field
	Battery           apijson.Field
	Fonts             apijson.Field
	MockWebRtc        apijson.Field
	MultimediaDevices apijson.Field
	Navigator         apijson.Field
	PluginsData       apijson.Field
	Screen            apijson.Field
	Slim              apijson.Field
	VideoCard         apijson.Field
	VideoCodecs       apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintBattery struct {
	Charging        bool                                                `json:"charging" api:"required,nullable"`
	ChargingTime    float64                                             `json:"chargingTime" api:"required,nullable"`
	DischargingTime float64                                             `json:"dischargingTime" api:"required,nullable"`
	Level           float64                                             `json:"level" api:"required,nullable"`
	JSON            profileNewResponseFingerprintFingerprintBatteryJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintBatteryJSON contains the JSON
// metadata for the struct [ProfileNewResponseFingerprintFingerprintBattery]
type profileNewResponseFingerprintFingerprintBatteryJSON struct {
	Charging        apijson.Field
	ChargingTime    apijson.Field
	DischargingTime apijson.Field
	Level           apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintBattery) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintBatteryJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintMultimediaDevices struct {
	Micros   []ProfileNewResponseFingerprintFingerprintMultimediaDevicesMicro   `json:"micros" api:"required"`
	Speakers []ProfileNewResponseFingerprintFingerprintMultimediaDevicesSpeaker `json:"speakers" api:"required"`
	Webcams  []ProfileNewResponseFingerprintFingerprintMultimediaDevicesWebcam  `json:"webcams" api:"required"`
	JSON     profileNewResponseFingerprintFingerprintMultimediaDevicesJSON      `json:"-"`
}

// profileNewResponseFingerprintFingerprintMultimediaDevicesJSON contains the
// JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintMultimediaDevices]
type profileNewResponseFingerprintFingerprintMultimediaDevicesJSON struct {
	Micros      apijson.Field
	Speakers    apijson.Field
	Webcams     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintMultimediaDevices) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintMultimediaDevicesJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintMultimediaDevicesMicro struct {
	DeviceID string                                                             `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                             `json:"groupId" api:"required,nullable"`
	Kind     string                                                             `json:"kind" api:"required,nullable"`
	Label    string                                                             `json:"label" api:"required,nullable"`
	JSON     profileNewResponseFingerprintFingerprintMultimediaDevicesMicroJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintMultimediaDevicesMicroJSON contains
// the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintMultimediaDevicesMicro]
type profileNewResponseFingerprintFingerprintMultimediaDevicesMicroJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintMultimediaDevicesMicro) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintMultimediaDevicesMicroJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintMultimediaDevicesSpeaker struct {
	DeviceID string                                                               `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                               `json:"groupId" api:"required,nullable"`
	Kind     string                                                               `json:"kind" api:"required,nullable"`
	Label    string                                                               `json:"label" api:"required,nullable"`
	JSON     profileNewResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON contains
// the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintMultimediaDevicesSpeaker]
type profileNewResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintMultimediaDevicesSpeaker) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintMultimediaDevicesWebcam struct {
	DeviceID string                                                              `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                              `json:"groupId" api:"required,nullable"`
	Kind     string                                                              `json:"kind" api:"required,nullable"`
	Label    string                                                              `json:"label" api:"required,nullable"`
	JSON     profileNewResponseFingerprintFingerprintMultimediaDevicesWebcamJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintMultimediaDevicesWebcamJSON contains
// the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintMultimediaDevicesWebcam]
type profileNewResponseFingerprintFingerprintMultimediaDevicesWebcamJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintMultimediaDevicesWebcam) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintMultimediaDevicesWebcamJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintNavigator struct {
	AppCodeName         string                                                           `json:"appCodeName" api:"required,nullable"`
	AppName             string                                                           `json:"appName" api:"required,nullable"`
	AppVersion          string                                                           `json:"appVersion" api:"required,nullable"`
	DeviceMemory        float64                                                          `json:"deviceMemory" api:"required,nullable"`
	ExtraProperties     ProfileNewResponseFingerprintFingerprintNavigatorExtraProperties `json:"extraProperties" api:"required"`
	HardwareConcurrency float64                                                          `json:"hardwareConcurrency" api:"required,nullable"`
	Language            string                                                           `json:"language" api:"required,nullable"`
	Languages           []string                                                         `json:"languages" api:"required"`
	MaxTouchPoints      float64                                                          `json:"maxTouchPoints" api:"required,nullable"`
	Oscpu               string                                                           `json:"oscpu" api:"required,nullable"`
	Platform            string                                                           `json:"platform" api:"required,nullable"`
	Product             string                                                           `json:"product" api:"required,nullable"`
	ProductSub          string                                                           `json:"productSub" api:"required,nullable"`
	UserAgent           string                                                           `json:"userAgent" api:"required,nullable"`
	UserAgentData       ProfileNewResponseFingerprintFingerprintNavigatorUserAgentData   `json:"userAgentData" api:"required"`
	Vendor              string                                                           `json:"vendor" api:"required,nullable"`
	VendorSub           string                                                           `json:"vendorSub" api:"required,nullable"`
	Webdriver           bool                                                             `json:"webdriver" api:"required,nullable"`
	DoNotTrack          string                                                           `json:"doNotTrack" api:"nullable"`
	JSON                profileNewResponseFingerprintFingerprintNavigatorJSON            `json:"-"`
}

// profileNewResponseFingerprintFingerprintNavigatorJSON contains the JSON
// metadata for the struct [ProfileNewResponseFingerprintFingerprintNavigator]
type profileNewResponseFingerprintFingerprintNavigatorJSON struct {
	AppCodeName         apijson.Field
	AppName             apijson.Field
	AppVersion          apijson.Field
	DeviceMemory        apijson.Field
	ExtraProperties     apijson.Field
	HardwareConcurrency apijson.Field
	Language            apijson.Field
	Languages           apijson.Field
	MaxTouchPoints      apijson.Field
	Oscpu               apijson.Field
	Platform            apijson.Field
	Product             apijson.Field
	ProductSub          apijson.Field
	UserAgent           apijson.Field
	UserAgentData       apijson.Field
	Vendor              apijson.Field
	VendorSub           apijson.Field
	Webdriver           apijson.Field
	DoNotTrack          apijson.Field
	raw                 string
	ExtraFields         map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintNavigator) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintNavigatorJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintNavigatorExtraProperties struct {
	GlobalPrivacyControl bool                                                                 `json:"globalPrivacyControl" api:"required,nullable"`
	InstalledApps        []string                                                             `json:"installedApps" api:"required"`
	PdfViewerEnabled     bool                                                                 `json:"pdfViewerEnabled" api:"required,nullable"`
	VendorFlavors        []string                                                             `json:"vendorFlavors" api:"required"`
	JSON                 profileNewResponseFingerprintFingerprintNavigatorExtraPropertiesJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintNavigatorExtraPropertiesJSON contains
// the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintNavigatorExtraProperties]
type profileNewResponseFingerprintFingerprintNavigatorExtraPropertiesJSON struct {
	GlobalPrivacyControl apijson.Field
	InstalledApps        apijson.Field
	PdfViewerEnabled     apijson.Field
	VendorFlavors        apijson.Field
	raw                  string
	ExtraFields          map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintNavigatorExtraProperties) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintNavigatorExtraPropertiesJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintNavigatorUserAgentData struct {
	Brands   []ProfileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrand `json:"brands" api:"required"`
	Mobile   bool                                                                  `json:"mobile" api:"required,nullable"`
	Platform string                                                                `json:"platform" api:"required,nullable"`
	JSON     profileNewResponseFingerprintFingerprintNavigatorUserAgentDataJSON    `json:"-"`
}

// profileNewResponseFingerprintFingerprintNavigatorUserAgentDataJSON contains
// the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintNavigatorUserAgentData]
type profileNewResponseFingerprintFingerprintNavigatorUserAgentDataJSON struct {
	Brands      apijson.Field
	Mobile      apijson.Field
	Platform    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintNavigatorUserAgentData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintNavigatorUserAgentDataJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrand struct {
	Brand   string                                                                  `json:"brand" api:"required,nullable"`
	Version string                                                                  `json:"version" api:"required,nullable"`
	JSON    profileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON
// contains the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrand]
type profileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON struct {
	Brand       apijson.Field
	Version     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrand) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintPluginsData struct {
	MimeTypes []string                                                    `json:"mimeTypes" api:"required"`
	Plugins   []ProfileNewResponseFingerprintFingerprintPluginsDataPlugin `json:"plugins" api:"required"`
	JSON      profileNewResponseFingerprintFingerprintPluginsDataJSON     `json:"-"`
}

// profileNewResponseFingerprintFingerprintPluginsDataJSON contains the JSON
// metadata for the struct [ProfileNewResponseFingerprintFingerprintPluginsData]
type profileNewResponseFingerprintFingerprintPluginsDataJSON struct {
	MimeTypes   apijson.Field
	Plugins     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintPluginsData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintPluginsDataJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintPluginsDataPlugin struct {
	Description string                                                              `json:"description" api:"required,nullable"`
	Filename    string                                                              `json:"filename" api:"required,nullable"`
	MimeTypes   []ProfileNewResponseFingerprintFingerprintPluginsDataPluginMimeType `json:"mimeTypes" api:"required"`
	Name        string                                                              `json:"name" api:"required,nullable"`
	JSON        profileNewResponseFingerprintFingerprintPluginsDataPluginJSON       `json:"-"`
}

// profileNewResponseFingerprintFingerprintPluginsDataPluginJSON contains the
// JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintPluginsDataPlugin]
type profileNewResponseFingerprintFingerprintPluginsDataPluginJSON struct {
	Description apijson.Field
	Filename    apijson.Field
	MimeTypes   apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintPluginsDataPlugin) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintPluginsDataPluginJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintPluginsDataPluginMimeType struct {
	Description   string                                                                `json:"description" api:"required,nullable"`
	EnabledPlugin string                                                                `json:"enabledPlugin" api:"required,nullable"`
	Suffixes      string                                                                `json:"suffixes" api:"required,nullable"`
	Type          string                                                                `json:"type" api:"required,nullable"`
	JSON          profileNewResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON
// contains the JSON metadata for the struct
// [ProfileNewResponseFingerprintFingerprintPluginsDataPluginMimeType]
type profileNewResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON struct {
	Description   apijson.Field
	EnabledPlugin apijson.Field
	Suffixes      apijson.Field
	Type          apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintPluginsDataPluginMimeType) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintScreen struct {
	AvailHeight      float64                                            `json:"availHeight" api:"required,nullable"`
	AvailLeft        float64                                            `json:"availLeft" api:"required,nullable"`
	AvailTop         float64                                            `json:"availTop" api:"required,nullable"`
	AvailWidth       float64                                            `json:"availWidth" api:"required,nullable"`
	ClientHeight     float64                                            `json:"clientHeight" api:"required,nullable"`
	ClientWidth      float64                                            `json:"clientWidth" api:"required,nullable"`
	ColorDepth       float64                                            `json:"colorDepth" api:"required,nullable"`
	DevicePixelRatio float64                                            `json:"devicePixelRatio" api:"required,nullable"`
	HasHdr           bool                                               `json:"hasHDR" api:"required,nullable"`
	Height           float64                                            `json:"height" api:"required,nullable"`
	InnerHeight      float64                                            `json:"innerHeight" api:"required,nullable"`
	InnerWidth       float64                                            `json:"innerWidth" api:"required,nullable"`
	OuterHeight      float64                                            `json:"outerHeight" api:"required,nullable"`
	OuterWidth       float64                                            `json:"outerWidth" api:"required,nullable"`
	PageXOffset      float64                                            `json:"pageXOffset" api:"required,nullable"`
	PageYOffset      float64                                            `json:"pageYOffset" api:"required,nullable"`
	PixelDepth       float64                                            `json:"pixelDepth" api:"required,nullable"`
	ScreenX          float64                                            `json:"screenX" api:"required,nullable"`
	Width            float64                                            `json:"width" api:"required,nullable"`
	JSON             profileNewResponseFingerprintFingerprintScreenJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintScreenJSON contains the JSON metadata
// for the struct [ProfileNewResponseFingerprintFingerprintScreen]
type profileNewResponseFingerprintFingerprintScreenJSON struct {
	AvailHeight      apijson.Field
	AvailLeft        apijson.Field
	AvailTop         apijson.Field
	AvailWidth       apijson.Field
	ClientHeight     apijson.Field
	ClientWidth      apijson.Field
	ColorDepth       apijson.Field
	DevicePixelRatio apijson.Field
	HasHdr           apijson.Field
	Height           apijson.Field
	InnerHeight      apijson.Field
	InnerWidth       apijson.Field
	OuterHeight      apijson.Field
	OuterWidth       apijson.Field
	PageXOffset      apijson.Field
	PageYOffset      apijson.Field
	PixelDepth       apijson.Field
	ScreenX          apijson.Field
	Width            apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintScreen) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintScreenJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintFingerprintVideoCard struct {
	Renderer string                                                `json:"renderer" api:"required,nullable"`
	Vendor   string                                                `json:"vendor" api:"required,nullable"`
	JSON     profileNewResponseFingerprintFingerprintVideoCardJSON `json:"-"`
}

// profileNewResponseFingerprintFingerprintVideoCardJSON contains the JSON
// metadata for the struct [ProfileNewResponseFingerprintFingerprintVideoCard]
type profileNewResponseFingerprintFingerprintVideoCardJSON struct {
	Renderer    apijson.Field
	Vendor      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintFingerprintVideoCard) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintFingerprintVideoCardJSON) RawJSON() string {
	return r.raw
}

type ProfileNewResponseFingerprintHeaders struct {
	UserAgent               string                                   `json:"user-agent" api:"required,nullable"`
	Accept                  string                                   `json:"accept" api:"nullable"`
	AcceptEncoding          string                                   `json:"accept-encoding" api:"nullable"`
	AcceptLanguage          string                                   `json:"accept-language" api:"nullable"`
	Dnt                     string                                   `json:"dnt" api:"nullable"`
	SecChUa                 string                                   `json:"sec-ch-ua" api:"nullable"`
	SecChUaMobile           string                                   `json:"sec-ch-ua-mobile" api:"nullable"`
	SecChUaPlatform         string                                   `json:"sec-ch-ua-platform" api:"nullable"`
	SecFetchDest            string                                   `json:"sec-fetch-dest" api:"nullable"`
	SecFetchMode            string                                   `json:"sec-fetch-mode" api:"nullable"`
	SecFetchSite            string                                   `json:"sec-fetch-site" api:"nullable"`
	SecFetchUser            string                                   `json:"sec-fetch-user" api:"nullable"`
	UpgradeInsecureRequests string                                   `json:"upgrade-insecure-requests" api:"nullable"`
	JSON                    profileNewResponseFingerprintHeadersJSON `json:"-"`
}

// profileNewResponseFingerprintHeadersJSON contains the JSON metadata for the
// struct [ProfileNewResponseFingerprintHeaders]
type profileNewResponseFingerprintHeadersJSON struct {
	UserAgent               apijson.Field
	Accept                  apijson.Field
	AcceptEncoding          apijson.Field
	AcceptLanguage          apijson.Field
	Dnt                     apijson.Field
	SecChUa                 apijson.Field
	SecChUaMobile           apijson.Field
	SecChUaPlatform         apijson.Field
	SecFetchDest            apijson.Field
	SecFetchMode            apijson.Field
	SecFetchSite            apijson.Field
	SecFetchUser            apijson.Field
	UpgradeInsecureRequests apijson.Field
	raw                     string
	ExtraFields             map[string]apijson.Field
}

func (r *ProfileNewResponseFingerprintHeaders) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileNewResponseFingerprintHeadersJSON) RawJSON() string {
	return r.raw
}

// The status of the profile
type ProfileNewResponseStatus string

const (
	ProfileNewResponseStatusUploading ProfileNewResponseStatus = "UPLOADING"
	ProfileNewResponseStatusReady     ProfileNewResponseStatus = "READY"
	ProfileNewResponseStatusFailed    ProfileNewResponseStatus = "FAILED"
)

func (r ProfileNewResponseStatus) IsKnown() bool {
	switch r {
	case ProfileNewResponseStatusUploading, ProfileNewResponseStatusReady, ProfileNewResponseStatusFailed:
		return true
	}
	return false
}

// Update an existing profile
func (r *ProfileService) Update(ctx context.Context, id string, params ProfileUpdateParams, opts ...option.RequestOption) (res *ProfileUpdateResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/profiles/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodPatch, path, params, &res, opts...)
	return res, err
}

type ProfileUpdateParams struct {
	// The user data directory associated with the profile
	UserDataDir param.Field[io.Reader] `json:"userDataDir" api:"required" format:"binary"`
	// Project to query profiles from
	QueryProjectID param.Field[string] `query:"projectId" format:"uuid"`
	// The dimensions associated with the profile
	Dimensions param.Field[ProfileUpdateParamsDimensions] `json:"dimensions"`
	// Project to create the profile in
	BodyProjectID param.Field[string] `json:"projectId" format:"uuid"`
	// The proxy associated with the profile
	ProxyURL param.Field[string] `json:"proxyUrl"`
	// JSON-encoded proxy configuration associated with the profile. For fixed IPs,
	// send {"type":"fixed","id":"fixed:<id>"}.
	UseProxy param.Field[string] `json:"useProxy"`
	// The user agent associated with the profile
	UserAgent param.Field[string] `json:"userAgent"`
}

func (r ProfileUpdateParams) MarshalMultipart() (data []byte, contentType string, err error) {
	buf := bytes.NewBuffer(nil)
	writer := multipart.NewWriter(buf)
	err = apiform.MarshalRoot(r, writer)
	if err != nil {
		writer.Close()
		return nil, "", err
	}
	err = writer.Close()
	if err != nil {
		return nil, "", err
	}
	return buf.Bytes(), writer.FormDataContentType(), nil
}

// URLQuery serializes [ProfileUpdateParams]'s query parameters as `url.Values`.
func (r ProfileUpdateParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

// The dimensions associated with the profile
type ProfileUpdateParamsDimensions struct {
	Height param.Field[float64] `json:"height" api:"required"`
	Width  param.Field[float64] `json:"width" api:"required"`
}

func (r ProfileUpdateParamsDimensions) MarshalJSON() (data []byte, err error) {
	return apijson.MarshalRoot(r)
}

type ProfileUpdateResponse struct {
	// The unique identifier for the profile
	ID string `json:"id" api:"required" format:"uuid"`
	// The date and time when the profile was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// The credentials configuration associated with the profile
	CredentialsConfig interface{} `json:"credentialsConfig" api:"required"`
	// The dimensions associated with the profile
	Dimensions ProfileUpdateResponseDimensions `json:"dimensions" api:"required,nullable"`
	// The extension IDs associated with the profile
	ExtensionIDs []string `json:"extensionIds" api:"required,nullable"`
	// The fingerprint associated with the profile
	Fingerprint ProfileUpdateResponseFingerprint `json:"fingerprint" api:"required,nullable"`
	// The project ID associated with the profile
	ProjectID string `json:"projectId" api:"required,nullable" format:"uuid"`
	// The last session ID associated with the profile
	SourceSessionID string `json:"sourceSessionId" api:"required,nullable" format:"uuid"`
	// The status of the profile
	Status ProfileUpdateResponseStatus `json:"status" api:"required"`
	// The date and time when the profile was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// The proxy configuration associated with the profile
	UseProxyConfig interface{} `json:"useProxyConfig" api:"required"`
	// The user agent associated with the profile
	UserAgent string                    `json:"userAgent" api:"required,nullable"`
	JSON      profileUpdateResponseJSON `json:"-"`
}

// profileUpdateResponseJSON contains the JSON metadata for the struct
// [ProfileUpdateResponse]
type profileUpdateResponseJSON struct {
	ID                apijson.Field
	CreatedAt         apijson.Field
	CredentialsConfig apijson.Field
	Dimensions        apijson.Field
	ExtensionIDs      apijson.Field
	Fingerprint       apijson.Field
	ProjectID         apijson.Field
	SourceSessionID   apijson.Field
	Status            apijson.Field
	UpdatedAt         apijson.Field
	UseProxyConfig    apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileUpdateResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseJSON) RawJSON() string {
	return r.raw
}

// The dimensions associated with the profile
type ProfileUpdateResponseDimensions struct {
	Height float64                             `json:"height" api:"required"`
	Width  float64                             `json:"width" api:"required"`
	JSON   profileUpdateResponseDimensionsJSON `json:"-"`
}

// profileUpdateResponseDimensionsJSON contains the JSON metadata for the struct
// [ProfileUpdateResponseDimensions]
type profileUpdateResponseDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseDimensionsJSON) RawJSON() string {
	return r.raw
}

// The fingerprint associated with the profile
type ProfileUpdateResponseFingerprint struct {
	Fingerprint ProfileUpdateResponseFingerprintFingerprint `json:"fingerprint" api:"required"`
	Headers     ProfileUpdateResponseFingerprintHeaders     `json:"headers" api:"required"`
	JSON        profileUpdateResponseFingerprintJSON        `json:"-"`
}

// profileUpdateResponseFingerprintJSON contains the JSON metadata for the
// struct [ProfileUpdateResponseFingerprint]
type profileUpdateResponseFingerprintJSON struct {
	Fingerprint apijson.Field
	Headers     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprint struct {
	AudioCodecs       map[string]string                                            `json:"audioCodecs" api:"required"`
	Battery           ProfileUpdateResponseFingerprintFingerprintBattery           `json:"battery" api:"required"`
	Fonts             []string                                                     `json:"fonts" api:"required"`
	MockWebRtc        bool                                                         `json:"mockWebRTC" api:"required,nullable"`
	MultimediaDevices ProfileUpdateResponseFingerprintFingerprintMultimediaDevices `json:"multimediaDevices" api:"required"`
	Navigator         ProfileUpdateResponseFingerprintFingerprintNavigator         `json:"navigator" api:"required"`
	PluginsData       ProfileUpdateResponseFingerprintFingerprintPluginsData       `json:"pluginsData" api:"required"`
	Screen            ProfileUpdateResponseFingerprintFingerprintScreen            `json:"screen" api:"required"`
	Slim              bool                                                         `json:"slim" api:"required,nullable"`
	VideoCard         ProfileUpdateResponseFingerprintFingerprintVideoCard         `json:"videoCard" api:"required"`
	VideoCodecs       map[string]string                                            `json:"videoCodecs" api:"required"`
	JSON              profileUpdateResponseFingerprintFingerprintJSON              `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintJSON contains the JSON metadata
// for the struct [ProfileUpdateResponseFingerprintFingerprint]
type profileUpdateResponseFingerprintFingerprintJSON struct {
	AudioCodecs       apijson.Field
	Battery           apijson.Field
	Fonts             apijson.Field
	MockWebRtc        apijson.Field
	MultimediaDevices apijson.Field
	Navigator         apijson.Field
	PluginsData       apijson.Field
	Screen            apijson.Field
	Slim              apijson.Field
	VideoCard         apijson.Field
	VideoCodecs       apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintBattery struct {
	Charging        bool                                                   `json:"charging" api:"required,nullable"`
	ChargingTime    float64                                                `json:"chargingTime" api:"required,nullable"`
	DischargingTime float64                                                `json:"dischargingTime" api:"required,nullable"`
	Level           float64                                                `json:"level" api:"required,nullable"`
	JSON            profileUpdateResponseFingerprintFingerprintBatteryJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintBatteryJSON contains the JSON
// metadata for the struct [ProfileUpdateResponseFingerprintFingerprintBattery]
type profileUpdateResponseFingerprintFingerprintBatteryJSON struct {
	Charging        apijson.Field
	ChargingTime    apijson.Field
	DischargingTime apijson.Field
	Level           apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintBattery) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintBatteryJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintMultimediaDevices struct {
	Micros   []ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro   `json:"micros" api:"required"`
	Speakers []ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker `json:"speakers" api:"required"`
	Webcams  []ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam  `json:"webcams" api:"required"`
	JSON     profileUpdateResponseFingerprintFingerprintMultimediaDevicesJSON      `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintMultimediaDevicesJSON contains the
// JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintMultimediaDevices]
type profileUpdateResponseFingerprintFingerprintMultimediaDevicesJSON struct {
	Micros      apijson.Field
	Speakers    apijson.Field
	Webcams     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintMultimediaDevices) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintMultimediaDevicesJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro struct {
	DeviceID string                                                                `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                `json:"groupId" api:"required,nullable"`
	Kind     string                                                                `json:"kind" api:"required,nullable"`
	Label    string                                                                `json:"label" api:"required,nullable"`
	JSON     profileUpdateResponseFingerprintFingerprintMultimediaDevicesMicroJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintMultimediaDevicesMicroJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro]
type profileUpdateResponseFingerprintFingerprintMultimediaDevicesMicroJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesMicro) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintMultimediaDevicesMicroJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker struct {
	DeviceID string                                                                  `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                  `json:"groupId" api:"required,nullable"`
	Kind     string                                                                  `json:"kind" api:"required,nullable"`
	Label    string                                                                  `json:"label" api:"required,nullable"`
	JSON     profileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker]
type profileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeaker) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam struct {
	DeviceID string                                                                 `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                 `json:"groupId" api:"required,nullable"`
	Kind     string                                                                 `json:"kind" api:"required,nullable"`
	Label    string                                                                 `json:"label" api:"required,nullable"`
	JSON     profileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcamJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcamJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam]
type profileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcamJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcam) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintMultimediaDevicesWebcamJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintNavigator struct {
	AppCodeName         string                                                              `json:"appCodeName" api:"required,nullable"`
	AppName             string                                                              `json:"appName" api:"required,nullable"`
	AppVersion          string                                                              `json:"appVersion" api:"required,nullable"`
	DeviceMemory        float64                                                             `json:"deviceMemory" api:"required,nullable"`
	ExtraProperties     ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties `json:"extraProperties" api:"required"`
	HardwareConcurrency float64                                                             `json:"hardwareConcurrency" api:"required,nullable"`
	Language            string                                                              `json:"language" api:"required,nullable"`
	Languages           []string                                                            `json:"languages" api:"required"`
	MaxTouchPoints      float64                                                             `json:"maxTouchPoints" api:"required,nullable"`
	Oscpu               string                                                              `json:"oscpu" api:"required,nullable"`
	Platform            string                                                              `json:"platform" api:"required,nullable"`
	Product             string                                                              `json:"product" api:"required,nullable"`
	ProductSub          string                                                              `json:"productSub" api:"required,nullable"`
	UserAgent           string                                                              `json:"userAgent" api:"required,nullable"`
	UserAgentData       ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData   `json:"userAgentData" api:"required"`
	Vendor              string                                                              `json:"vendor" api:"required,nullable"`
	VendorSub           string                                                              `json:"vendorSub" api:"required,nullable"`
	Webdriver           bool                                                                `json:"webdriver" api:"required,nullable"`
	DoNotTrack          string                                                              `json:"doNotTrack" api:"nullable"`
	JSON                profileUpdateResponseFingerprintFingerprintNavigatorJSON            `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintNavigatorJSON contains the JSON
// metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintNavigator]
type profileUpdateResponseFingerprintFingerprintNavigatorJSON struct {
	AppCodeName         apijson.Field
	AppName             apijson.Field
	AppVersion          apijson.Field
	DeviceMemory        apijson.Field
	ExtraProperties     apijson.Field
	HardwareConcurrency apijson.Field
	Language            apijson.Field
	Languages           apijson.Field
	MaxTouchPoints      apijson.Field
	Oscpu               apijson.Field
	Platform            apijson.Field
	Product             apijson.Field
	ProductSub          apijson.Field
	UserAgent           apijson.Field
	UserAgentData       apijson.Field
	Vendor              apijson.Field
	VendorSub           apijson.Field
	Webdriver           apijson.Field
	DoNotTrack          apijson.Field
	raw                 string
	ExtraFields         map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintNavigator) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintNavigatorJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties struct {
	GlobalPrivacyControl bool                                                                    `json:"globalPrivacyControl" api:"required,nullable"`
	InstalledApps        []string                                                                `json:"installedApps" api:"required"`
	PdfViewerEnabled     bool                                                                    `json:"pdfViewerEnabled" api:"required,nullable"`
	VendorFlavors        []string                                                                `json:"vendorFlavors" api:"required"`
	JSON                 profileUpdateResponseFingerprintFingerprintNavigatorExtraPropertiesJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintNavigatorExtraPropertiesJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties]
type profileUpdateResponseFingerprintFingerprintNavigatorExtraPropertiesJSON struct {
	GlobalPrivacyControl apijson.Field
	InstalledApps        apijson.Field
	PdfViewerEnabled     apijson.Field
	VendorFlavors        apijson.Field
	raw                  string
	ExtraFields          map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintNavigatorExtraProperties) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintNavigatorExtraPropertiesJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData struct {
	Brands   []ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand `json:"brands" api:"required"`
	Mobile   bool                                                                     `json:"mobile" api:"required,nullable"`
	Platform string                                                                   `json:"platform" api:"required,nullable"`
	JSON     profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataJSON    `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData]
type profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataJSON struct {
	Brands      apijson.Field
	Mobile      apijson.Field
	Platform    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand struct {
	Brand   string                                                                     `json:"brand" api:"required,nullable"`
	Version string                                                                     `json:"version" api:"required,nullable"`
	JSON    profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand]
type profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON struct {
	Brand       apijson.Field
	Version     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrand) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintPluginsData struct {
	MimeTypes []string                                                       `json:"mimeTypes" api:"required"`
	Plugins   []ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin `json:"plugins" api:"required"`
	JSON      profileUpdateResponseFingerprintFingerprintPluginsDataJSON     `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintPluginsDataJSON contains the JSON
// metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintPluginsData]
type profileUpdateResponseFingerprintFingerprintPluginsDataJSON struct {
	MimeTypes   apijson.Field
	Plugins     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintPluginsData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintPluginsDataJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin struct {
	Description string                                                                 `json:"description" api:"required,nullable"`
	Filename    string                                                                 `json:"filename" api:"required,nullable"`
	MimeTypes   []ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType `json:"mimeTypes" api:"required"`
	Name        string                                                                 `json:"name" api:"required,nullable"`
	JSON        profileUpdateResponseFingerprintFingerprintPluginsDataPluginJSON       `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintPluginsDataPluginJSON contains the
// JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin]
type profileUpdateResponseFingerprintFingerprintPluginsDataPluginJSON struct {
	Description apijson.Field
	Filename    apijson.Field
	MimeTypes   apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintPluginsDataPlugin) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintPluginsDataPluginJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType struct {
	Description   string                                                                   `json:"description" api:"required,nullable"`
	EnabledPlugin string                                                                   `json:"enabledPlugin" api:"required,nullable"`
	Suffixes      string                                                                   `json:"suffixes" api:"required,nullable"`
	Type          string                                                                   `json:"type" api:"required,nullable"`
	JSON          profileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON
// contains the JSON metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType]
type profileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON struct {
	Description   apijson.Field
	EnabledPlugin apijson.Field
	Suffixes      apijson.Field
	Type          apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeType) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintScreen struct {
	AvailHeight      float64                                               `json:"availHeight" api:"required,nullable"`
	AvailLeft        float64                                               `json:"availLeft" api:"required,nullable"`
	AvailTop         float64                                               `json:"availTop" api:"required,nullable"`
	AvailWidth       float64                                               `json:"availWidth" api:"required,nullable"`
	ClientHeight     float64                                               `json:"clientHeight" api:"required,nullable"`
	ClientWidth      float64                                               `json:"clientWidth" api:"required,nullable"`
	ColorDepth       float64                                               `json:"colorDepth" api:"required,nullable"`
	DevicePixelRatio float64                                               `json:"devicePixelRatio" api:"required,nullable"`
	HasHdr           bool                                                  `json:"hasHDR" api:"required,nullable"`
	Height           float64                                               `json:"height" api:"required,nullable"`
	InnerHeight      float64                                               `json:"innerHeight" api:"required,nullable"`
	InnerWidth       float64                                               `json:"innerWidth" api:"required,nullable"`
	OuterHeight      float64                                               `json:"outerHeight" api:"required,nullable"`
	OuterWidth       float64                                               `json:"outerWidth" api:"required,nullable"`
	PageXOffset      float64                                               `json:"pageXOffset" api:"required,nullable"`
	PageYOffset      float64                                               `json:"pageYOffset" api:"required,nullable"`
	PixelDepth       float64                                               `json:"pixelDepth" api:"required,nullable"`
	ScreenX          float64                                               `json:"screenX" api:"required,nullable"`
	Width            float64                                               `json:"width" api:"required,nullable"`
	JSON             profileUpdateResponseFingerprintFingerprintScreenJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintScreenJSON contains the JSON
// metadata for the struct [ProfileUpdateResponseFingerprintFingerprintScreen]
type profileUpdateResponseFingerprintFingerprintScreenJSON struct {
	AvailHeight      apijson.Field
	AvailLeft        apijson.Field
	AvailTop         apijson.Field
	AvailWidth       apijson.Field
	ClientHeight     apijson.Field
	ClientWidth      apijson.Field
	ColorDepth       apijson.Field
	DevicePixelRatio apijson.Field
	HasHdr           apijson.Field
	Height           apijson.Field
	InnerHeight      apijson.Field
	InnerWidth       apijson.Field
	OuterHeight      apijson.Field
	OuterWidth       apijson.Field
	PageXOffset      apijson.Field
	PageYOffset      apijson.Field
	PixelDepth       apijson.Field
	ScreenX          apijson.Field
	Width            apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintScreen) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintScreenJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintFingerprintVideoCard struct {
	Renderer string                                                   `json:"renderer" api:"required,nullable"`
	Vendor   string                                                   `json:"vendor" api:"required,nullable"`
	JSON     profileUpdateResponseFingerprintFingerprintVideoCardJSON `json:"-"`
}

// profileUpdateResponseFingerprintFingerprintVideoCardJSON contains the JSON
// metadata for the struct
// [ProfileUpdateResponseFingerprintFingerprintVideoCard]
type profileUpdateResponseFingerprintFingerprintVideoCardJSON struct {
	Renderer    apijson.Field
	Vendor      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintFingerprintVideoCard) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintFingerprintVideoCardJSON) RawJSON() string {
	return r.raw
}

type ProfileUpdateResponseFingerprintHeaders struct {
	UserAgent               string                                      `json:"user-agent" api:"required,nullable"`
	Accept                  string                                      `json:"accept" api:"nullable"`
	AcceptEncoding          string                                      `json:"accept-encoding" api:"nullable"`
	AcceptLanguage          string                                      `json:"accept-language" api:"nullable"`
	Dnt                     string                                      `json:"dnt" api:"nullable"`
	SecChUa                 string                                      `json:"sec-ch-ua" api:"nullable"`
	SecChUaMobile           string                                      `json:"sec-ch-ua-mobile" api:"nullable"`
	SecChUaPlatform         string                                      `json:"sec-ch-ua-platform" api:"nullable"`
	SecFetchDest            string                                      `json:"sec-fetch-dest" api:"nullable"`
	SecFetchMode            string                                      `json:"sec-fetch-mode" api:"nullable"`
	SecFetchSite            string                                      `json:"sec-fetch-site" api:"nullable"`
	SecFetchUser            string                                      `json:"sec-fetch-user" api:"nullable"`
	UpgradeInsecureRequests string                                      `json:"upgrade-insecure-requests" api:"nullable"`
	JSON                    profileUpdateResponseFingerprintHeadersJSON `json:"-"`
}

// profileUpdateResponseFingerprintHeadersJSON contains the JSON metadata for
// the struct [ProfileUpdateResponseFingerprintHeaders]
type profileUpdateResponseFingerprintHeadersJSON struct {
	UserAgent               apijson.Field
	Accept                  apijson.Field
	AcceptEncoding          apijson.Field
	AcceptLanguage          apijson.Field
	Dnt                     apijson.Field
	SecChUa                 apijson.Field
	SecChUaMobile           apijson.Field
	SecChUaPlatform         apijson.Field
	SecFetchDest            apijson.Field
	SecFetchMode            apijson.Field
	SecFetchSite            apijson.Field
	SecFetchUser            apijson.Field
	UpgradeInsecureRequests apijson.Field
	raw                     string
	ExtraFields             map[string]apijson.Field
}

func (r *ProfileUpdateResponseFingerprintHeaders) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileUpdateResponseFingerprintHeadersJSON) RawJSON() string {
	return r.raw
}

// The status of the profile
type ProfileUpdateResponseStatus string

const (
	ProfileUpdateResponseStatusUploading ProfileUpdateResponseStatus = "UPLOADING"
	ProfileUpdateResponseStatusReady     ProfileUpdateResponseStatus = "READY"
	ProfileUpdateResponseStatusFailed    ProfileUpdateResponseStatus = "FAILED"
)

func (r ProfileUpdateResponseStatus) IsKnown() bool {
	switch r {
	case ProfileUpdateResponseStatusUploading, ProfileUpdateResponseStatusReady, ProfileUpdateResponseStatusFailed:
		return true
	}
	return false
}

// Retrieve a list of all profiles
func (r *ProfileService) List(ctx context.Context, query ProfileListParams, opts ...option.RequestOption) (res *ProfileListResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	path := "v1/profiles"
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type ProfileListParams struct {
	// Project to query profiles from
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [ProfileListParams]'s query parameters as `url.Values`.
func (r ProfileListParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type ProfileListResponse struct {
	// The total number of profiles
	Count float64 `json:"count" api:"required"`
	// The list of profiles
	Profiles []ProfileListResponseProfile `json:"profiles" api:"required"`
	JSON     profileListResponseJSON      `json:"-"`
}

// profileListResponseJSON contains the JSON metadata for the struct
// [ProfileListResponse]
type profileListResponseJSON struct {
	Count       apijson.Field
	Profiles    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseJSON) RawJSON() string {
	return r.raw
}

// The list of profiles
type ProfileListResponseProfile struct {
	// The unique identifier for the profile
	ID string `json:"id" api:"required" format:"uuid"`
	// The date and time when the profile was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// The credentials configuration associated with the profile
	CredentialsConfig interface{} `json:"credentialsConfig" api:"required"`
	// The dimensions associated with the profile
	Dimensions ProfileListResponseProfileDimensions `json:"dimensions" api:"required,nullable"`
	// The extension IDs associated with the profile
	ExtensionIDs []string `json:"extensionIds" api:"required,nullable"`
	// The fingerprint associated with the profile
	Fingerprint ProfileListResponseProfileFingerprint `json:"fingerprint" api:"required,nullable"`
	// The project ID associated with the profile
	ProjectID string `json:"projectId" api:"required,nullable" format:"uuid"`
	// The last session ID associated with the profile
	SourceSessionID string `json:"sourceSessionId" api:"required,nullable" format:"uuid"`
	// The status of the profile
	Status ProfileListResponseProfileStatus `json:"status" api:"required"`
	// The date and time when the profile was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// The proxy configuration associated with the profile
	UseProxyConfig interface{} `json:"useProxyConfig" api:"required"`
	// The user agent associated with the profile
	UserAgent string                         `json:"userAgent" api:"required,nullable"`
	JSON      profileListResponseProfileJSON `json:"-"`
}

// profileListResponseProfileJSON contains the JSON metadata for the struct
// [ProfileListResponseProfile]
type profileListResponseProfileJSON struct {
	ID                apijson.Field
	CreatedAt         apijson.Field
	CredentialsConfig apijson.Field
	Dimensions        apijson.Field
	ExtensionIDs      apijson.Field
	Fingerprint       apijson.Field
	ProjectID         apijson.Field
	SourceSessionID   apijson.Field
	Status            apijson.Field
	UpdatedAt         apijson.Field
	UseProxyConfig    apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileListResponseProfile) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileJSON) RawJSON() string {
	return r.raw
}

// The dimensions associated with the profile
type ProfileListResponseProfileDimensions struct {
	Height float64                                  `json:"height" api:"required"`
	Width  float64                                  `json:"width" api:"required"`
	JSON   profileListResponseProfileDimensionsJSON `json:"-"`
}

// profileListResponseProfileDimensionsJSON contains the JSON metadata for the
// struct [ProfileListResponseProfileDimensions]
type profileListResponseProfileDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileDimensionsJSON) RawJSON() string {
	return r.raw
}

// The fingerprint associated with the profile
type ProfileListResponseProfileFingerprint struct {
	Fingerprint ProfileListResponseProfileFingerprintFingerprint `json:"fingerprint" api:"required"`
	Headers     ProfileListResponseProfileFingerprintHeaders     `json:"headers" api:"required"`
	JSON        profileListResponseProfileFingerprintJSON        `json:"-"`
}

// profileListResponseProfileFingerprintJSON contains the JSON metadata for the
// struct [ProfileListResponseProfileFingerprint]
type profileListResponseProfileFingerprintJSON struct {
	Fingerprint apijson.Field
	Headers     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprint struct {
	AudioCodecs       map[string]string                                                 `json:"audioCodecs" api:"required"`
	Battery           ProfileListResponseProfileFingerprintFingerprintBattery           `json:"battery" api:"required"`
	Fonts             []string                                                          `json:"fonts" api:"required"`
	MockWebRtc        bool                                                              `json:"mockWebRTC" api:"required,nullable"`
	MultimediaDevices ProfileListResponseProfileFingerprintFingerprintMultimediaDevices `json:"multimediaDevices" api:"required"`
	Navigator         ProfileListResponseProfileFingerprintFingerprintNavigator         `json:"navigator" api:"required"`
	PluginsData       ProfileListResponseProfileFingerprintFingerprintPluginsData       `json:"pluginsData" api:"required"`
	Screen            ProfileListResponseProfileFingerprintFingerprintScreen            `json:"screen" api:"required"`
	Slim              bool                                                              `json:"slim" api:"required,nullable"`
	VideoCard         ProfileListResponseProfileFingerprintFingerprintVideoCard         `json:"videoCard" api:"required"`
	VideoCodecs       map[string]string                                                 `json:"videoCodecs" api:"required"`
	JSON              profileListResponseProfileFingerprintFingerprintJSON              `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintJSON contains the JSON
// metadata for the struct [ProfileListResponseProfileFingerprintFingerprint]
type profileListResponseProfileFingerprintFingerprintJSON struct {
	AudioCodecs       apijson.Field
	Battery           apijson.Field
	Fonts             apijson.Field
	MockWebRtc        apijson.Field
	MultimediaDevices apijson.Field
	Navigator         apijson.Field
	PluginsData       apijson.Field
	Screen            apijson.Field
	Slim              apijson.Field
	VideoCard         apijson.Field
	VideoCodecs       apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintBattery struct {
	Charging        bool                                                        `json:"charging" api:"required,nullable"`
	ChargingTime    float64                                                     `json:"chargingTime" api:"required,nullable"`
	DischargingTime float64                                                     `json:"dischargingTime" api:"required,nullable"`
	Level           float64                                                     `json:"level" api:"required,nullable"`
	JSON            profileListResponseProfileFingerprintFingerprintBatteryJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintBatteryJSON contains the JSON
// metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintBattery]
type profileListResponseProfileFingerprintFingerprintBatteryJSON struct {
	Charging        apijson.Field
	ChargingTime    apijson.Field
	DischargingTime apijson.Field
	Level           apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintBattery) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintBatteryJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintMultimediaDevices struct {
	Micros   []ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro   `json:"micros" api:"required"`
	Speakers []ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker `json:"speakers" api:"required"`
	Webcams  []ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam  `json:"webcams" api:"required"`
	JSON     profileListResponseProfileFingerprintFingerprintMultimediaDevicesJSON      `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintMultimediaDevicesJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintMultimediaDevices]
type profileListResponseProfileFingerprintFingerprintMultimediaDevicesJSON struct {
	Micros      apijson.Field
	Speakers    apijson.Field
	Webcams     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintMultimediaDevices) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintMultimediaDevicesJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro struct {
	DeviceID string                                                                     `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                     `json:"groupId" api:"required,nullable"`
	Kind     string                                                                     `json:"kind" api:"required,nullable"`
	Label    string                                                                     `json:"label" api:"required,nullable"`
	JSON     profileListResponseProfileFingerprintFingerprintMultimediaDevicesMicroJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintMultimediaDevicesMicroJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro]
type profileListResponseProfileFingerprintFingerprintMultimediaDevicesMicroJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesMicro) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintMultimediaDevicesMicroJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker struct {
	DeviceID string                                                                       `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                       `json:"groupId" api:"required,nullable"`
	Kind     string                                                                       `json:"kind" api:"required,nullable"`
	Label    string                                                                       `json:"label" api:"required,nullable"`
	JSON     profileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeakerJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeakerJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker]
type profileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeakerJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeaker) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintMultimediaDevicesSpeakerJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam struct {
	DeviceID string                                                                      `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                                      `json:"groupId" api:"required,nullable"`
	Kind     string                                                                      `json:"kind" api:"required,nullable"`
	Label    string                                                                      `json:"label" api:"required,nullable"`
	JSON     profileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcamJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcamJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam]
type profileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcamJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcam) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintMultimediaDevicesWebcamJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintNavigator struct {
	AppCodeName         string                                                                   `json:"appCodeName" api:"required,nullable"`
	AppName             string                                                                   `json:"appName" api:"required,nullable"`
	AppVersion          string                                                                   `json:"appVersion" api:"required,nullable"`
	DeviceMemory        float64                                                                  `json:"deviceMemory" api:"required,nullable"`
	ExtraProperties     ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties `json:"extraProperties" api:"required"`
	HardwareConcurrency float64                                                                  `json:"hardwareConcurrency" api:"required,nullable"`
	Language            string                                                                   `json:"language" api:"required,nullable"`
	Languages           []string                                                                 `json:"languages" api:"required"`
	MaxTouchPoints      float64                                                                  `json:"maxTouchPoints" api:"required,nullable"`
	Oscpu               string                                                                   `json:"oscpu" api:"required,nullable"`
	Platform            string                                                                   `json:"platform" api:"required,nullable"`
	Product             string                                                                   `json:"product" api:"required,nullable"`
	ProductSub          string                                                                   `json:"productSub" api:"required,nullable"`
	UserAgent           string                                                                   `json:"userAgent" api:"required,nullable"`
	UserAgentData       ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData   `json:"userAgentData" api:"required"`
	Vendor              string                                                                   `json:"vendor" api:"required,nullable"`
	VendorSub           string                                                                   `json:"vendorSub" api:"required,nullable"`
	Webdriver           bool                                                                     `json:"webdriver" api:"required,nullable"`
	DoNotTrack          string                                                                   `json:"doNotTrack" api:"nullable"`
	JSON                profileListResponseProfileFingerprintFingerprintNavigatorJSON            `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintNavigatorJSON contains the
// JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintNavigator]
type profileListResponseProfileFingerprintFingerprintNavigatorJSON struct {
	AppCodeName         apijson.Field
	AppName             apijson.Field
	AppVersion          apijson.Field
	DeviceMemory        apijson.Field
	ExtraProperties     apijson.Field
	HardwareConcurrency apijson.Field
	Language            apijson.Field
	Languages           apijson.Field
	MaxTouchPoints      apijson.Field
	Oscpu               apijson.Field
	Platform            apijson.Field
	Product             apijson.Field
	ProductSub          apijson.Field
	UserAgent           apijson.Field
	UserAgentData       apijson.Field
	Vendor              apijson.Field
	VendorSub           apijson.Field
	Webdriver           apijson.Field
	DoNotTrack          apijson.Field
	raw                 string
	ExtraFields         map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintNavigator) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintNavigatorJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties struct {
	GlobalPrivacyControl bool                                                                         `json:"globalPrivacyControl" api:"required,nullable"`
	InstalledApps        []string                                                                     `json:"installedApps" api:"required"`
	PdfViewerEnabled     bool                                                                         `json:"pdfViewerEnabled" api:"required,nullable"`
	VendorFlavors        []string                                                                     `json:"vendorFlavors" api:"required"`
	JSON                 profileListResponseProfileFingerprintFingerprintNavigatorExtraPropertiesJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintNavigatorExtraPropertiesJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties]
type profileListResponseProfileFingerprintFingerprintNavigatorExtraPropertiesJSON struct {
	GlobalPrivacyControl apijson.Field
	InstalledApps        apijson.Field
	PdfViewerEnabled     apijson.Field
	VendorFlavors        apijson.Field
	raw                  string
	ExtraFields          map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintNavigatorExtraProperties) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintNavigatorExtraPropertiesJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData struct {
	Brands   []ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand `json:"brands" api:"required"`
	Mobile   bool                                                                          `json:"mobile" api:"required,nullable"`
	Platform string                                                                        `json:"platform" api:"required,nullable"`
	JSON     profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataJSON    `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData]
type profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataJSON struct {
	Brands      apijson.Field
	Mobile      apijson.Field
	Platform    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand struct {
	Brand   string                                                                          `json:"brand" api:"required,nullable"`
	Version string                                                                          `json:"version" api:"required,nullable"`
	JSON    profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrandJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrandJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand]
type profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrandJSON struct {
	Brand       apijson.Field
	Version     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrand) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintNavigatorUserAgentDataBrandJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintPluginsData struct {
	MimeTypes []string                                                            `json:"mimeTypes" api:"required"`
	Plugins   []ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin `json:"plugins" api:"required"`
	JSON      profileListResponseProfileFingerprintFingerprintPluginsDataJSON     `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintPluginsDataJSON contains the
// JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintPluginsData]
type profileListResponseProfileFingerprintFingerprintPluginsDataJSON struct {
	MimeTypes   apijson.Field
	Plugins     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintPluginsData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintPluginsDataJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin struct {
	Description string                                                                      `json:"description" api:"required,nullable"`
	Filename    string                                                                      `json:"filename" api:"required,nullable"`
	MimeTypes   []ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType `json:"mimeTypes" api:"required"`
	Name        string                                                                      `json:"name" api:"required,nullable"`
	JSON        profileListResponseProfileFingerprintFingerprintPluginsDataPluginJSON       `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintPluginsDataPluginJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin]
type profileListResponseProfileFingerprintFingerprintPluginsDataPluginJSON struct {
	Description apijson.Field
	Filename    apijson.Field
	MimeTypes   apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintPluginsDataPlugin) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintPluginsDataPluginJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType struct {
	Description   string                                                                        `json:"description" api:"required,nullable"`
	EnabledPlugin string                                                                        `json:"enabledPlugin" api:"required,nullable"`
	Suffixes      string                                                                        `json:"suffixes" api:"required,nullable"`
	Type          string                                                                        `json:"type" api:"required,nullable"`
	JSON          profileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeTypeJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeTypeJSON
// contains the JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType]
type profileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeTypeJSON struct {
	Description   apijson.Field
	EnabledPlugin apijson.Field
	Suffixes      apijson.Field
	Type          apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeType) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintPluginsDataPluginMimeTypeJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintScreen struct {
	AvailHeight      float64                                                    `json:"availHeight" api:"required,nullable"`
	AvailLeft        float64                                                    `json:"availLeft" api:"required,nullable"`
	AvailTop         float64                                                    `json:"availTop" api:"required,nullable"`
	AvailWidth       float64                                                    `json:"availWidth" api:"required,nullable"`
	ClientHeight     float64                                                    `json:"clientHeight" api:"required,nullable"`
	ClientWidth      float64                                                    `json:"clientWidth" api:"required,nullable"`
	ColorDepth       float64                                                    `json:"colorDepth" api:"required,nullable"`
	DevicePixelRatio float64                                                    `json:"devicePixelRatio" api:"required,nullable"`
	HasHdr           bool                                                       `json:"hasHDR" api:"required,nullable"`
	Height           float64                                                    `json:"height" api:"required,nullable"`
	InnerHeight      float64                                                    `json:"innerHeight" api:"required,nullable"`
	InnerWidth       float64                                                    `json:"innerWidth" api:"required,nullable"`
	OuterHeight      float64                                                    `json:"outerHeight" api:"required,nullable"`
	OuterWidth       float64                                                    `json:"outerWidth" api:"required,nullable"`
	PageXOffset      float64                                                    `json:"pageXOffset" api:"required,nullable"`
	PageYOffset      float64                                                    `json:"pageYOffset" api:"required,nullable"`
	PixelDepth       float64                                                    `json:"pixelDepth" api:"required,nullable"`
	ScreenX          float64                                                    `json:"screenX" api:"required,nullable"`
	Width            float64                                                    `json:"width" api:"required,nullable"`
	JSON             profileListResponseProfileFingerprintFingerprintScreenJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintScreenJSON contains the JSON
// metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintScreen]
type profileListResponseProfileFingerprintFingerprintScreenJSON struct {
	AvailHeight      apijson.Field
	AvailLeft        apijson.Field
	AvailTop         apijson.Field
	AvailWidth       apijson.Field
	ClientHeight     apijson.Field
	ClientWidth      apijson.Field
	ColorDepth       apijson.Field
	DevicePixelRatio apijson.Field
	HasHdr           apijson.Field
	Height           apijson.Field
	InnerHeight      apijson.Field
	InnerWidth       apijson.Field
	OuterHeight      apijson.Field
	OuterWidth       apijson.Field
	PageXOffset      apijson.Field
	PageYOffset      apijson.Field
	PixelDepth       apijson.Field
	ScreenX          apijson.Field
	Width            apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintScreen) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintScreenJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintFingerprintVideoCard struct {
	Renderer string                                                        `json:"renderer" api:"required,nullable"`
	Vendor   string                                                        `json:"vendor" api:"required,nullable"`
	JSON     profileListResponseProfileFingerprintFingerprintVideoCardJSON `json:"-"`
}

// profileListResponseProfileFingerprintFingerprintVideoCardJSON contains the
// JSON metadata for the struct
// [ProfileListResponseProfileFingerprintFingerprintVideoCard]
type profileListResponseProfileFingerprintFingerprintVideoCardJSON struct {
	Renderer    apijson.Field
	Vendor      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintFingerprintVideoCard) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintFingerprintVideoCardJSON) RawJSON() string {
	return r.raw
}

type ProfileListResponseProfileFingerprintHeaders struct {
	UserAgent               string                                           `json:"user-agent" api:"required,nullable"`
	Accept                  string                                           `json:"accept" api:"nullable"`
	AcceptEncoding          string                                           `json:"accept-encoding" api:"nullable"`
	AcceptLanguage          string                                           `json:"accept-language" api:"nullable"`
	Dnt                     string                                           `json:"dnt" api:"nullable"`
	SecChUa                 string                                           `json:"sec-ch-ua" api:"nullable"`
	SecChUaMobile           string                                           `json:"sec-ch-ua-mobile" api:"nullable"`
	SecChUaPlatform         string                                           `json:"sec-ch-ua-platform" api:"nullable"`
	SecFetchDest            string                                           `json:"sec-fetch-dest" api:"nullable"`
	SecFetchMode            string                                           `json:"sec-fetch-mode" api:"nullable"`
	SecFetchSite            string                                           `json:"sec-fetch-site" api:"nullable"`
	SecFetchUser            string                                           `json:"sec-fetch-user" api:"nullable"`
	UpgradeInsecureRequests string                                           `json:"upgrade-insecure-requests" api:"nullable"`
	JSON                    profileListResponseProfileFingerprintHeadersJSON `json:"-"`
}

// profileListResponseProfileFingerprintHeadersJSON contains the JSON metadata
// for the struct [ProfileListResponseProfileFingerprintHeaders]
type profileListResponseProfileFingerprintHeadersJSON struct {
	UserAgent               apijson.Field
	Accept                  apijson.Field
	AcceptEncoding          apijson.Field
	AcceptLanguage          apijson.Field
	Dnt                     apijson.Field
	SecChUa                 apijson.Field
	SecChUaMobile           apijson.Field
	SecChUaPlatform         apijson.Field
	SecFetchDest            apijson.Field
	SecFetchMode            apijson.Field
	SecFetchSite            apijson.Field
	SecFetchUser            apijson.Field
	UpgradeInsecureRequests apijson.Field
	raw                     string
	ExtraFields             map[string]apijson.Field
}

func (r *ProfileListResponseProfileFingerprintHeaders) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileListResponseProfileFingerprintHeadersJSON) RawJSON() string {
	return r.raw
}

// The status of the profile
type ProfileListResponseProfileStatus string

const (
	ProfileListResponseProfileStatusUploading ProfileListResponseProfileStatus = "UPLOADING"
	ProfileListResponseProfileStatusReady     ProfileListResponseProfileStatus = "READY"
	ProfileListResponseProfileStatusFailed    ProfileListResponseProfileStatus = "FAILED"
)

func (r ProfileListResponseProfileStatus) IsKnown() bool {
	switch r {
	case ProfileListResponseProfileStatusUploading, ProfileListResponseProfileStatusReady, ProfileListResponseProfileStatusFailed:
		return true
	}
	return false
}

// Retrieve a profile by ID
func (r *ProfileService) Get(ctx context.Context, id string, query ProfileGetParams, opts ...option.RequestOption) (res *ProfileGetResponse, err error) {
	opts = slices.Concat(r.Options, opts)
	if id == "" {
		err = errors.New("missing required id parameter")
		return nil, err
	}
	path := fmt.Sprintf("v1/profiles/%s", url.PathEscape(id))
	err = requestconfig.ExecuteNewRequest(ctx, http.MethodGet, path, query, &res, opts...)
	return res, err
}

type ProfileGetParams struct {
	// Project to query profiles from
	ProjectID param.Field[string] `query:"projectId" format:"uuid"`
}

// URLQuery serializes [ProfileGetParams]'s query parameters as `url.Values`.
func (r ProfileGetParams) URLQuery() (v url.Values) {
	return apiquery.MarshalWithSettings(r, apiquery.QuerySettings{
		ArrayFormat:  apiquery.ArrayQueryFormatRepeat,
		NestedFormat: apiquery.NestedQueryFormatBrackets,
	})
}

type ProfileGetResponse struct {
	// The unique identifier for the profile
	ID string `json:"id" api:"required" format:"uuid"`
	// The date and time when the profile was created
	CreatedAt time.Time `json:"createdAt" api:"required" format:"date-time"`
	// The credentials configuration associated with the profile
	CredentialsConfig interface{} `json:"credentialsConfig" api:"required"`
	// The dimensions associated with the profile
	Dimensions ProfileGetResponseDimensions `json:"dimensions" api:"required,nullable"`
	// The extension IDs associated with the profile
	ExtensionIDs []string `json:"extensionIds" api:"required,nullable"`
	// The fingerprint associated with the profile
	Fingerprint ProfileGetResponseFingerprint `json:"fingerprint" api:"required,nullable"`
	// The project ID associated with the profile
	ProjectID string `json:"projectId" api:"required,nullable" format:"uuid"`
	// The last session ID associated with the profile
	SourceSessionID string `json:"sourceSessionId" api:"required,nullable" format:"uuid"`
	// The status of the profile
	Status ProfileGetResponseStatus `json:"status" api:"required"`
	// The date and time when the profile was last updated
	UpdatedAt time.Time `json:"updatedAt" api:"required" format:"date-time"`
	// The proxy configuration associated with the profile
	UseProxyConfig interface{} `json:"useProxyConfig" api:"required"`
	// The user agent associated with the profile
	UserAgent string                 `json:"userAgent" api:"required,nullable"`
	JSON      profileGetResponseJSON `json:"-"`
}

// profileGetResponseJSON contains the JSON metadata for the struct
// [ProfileGetResponse]
type profileGetResponseJSON struct {
	ID                apijson.Field
	CreatedAt         apijson.Field
	CredentialsConfig apijson.Field
	Dimensions        apijson.Field
	ExtensionIDs      apijson.Field
	Fingerprint       apijson.Field
	ProjectID         apijson.Field
	SourceSessionID   apijson.Field
	Status            apijson.Field
	UpdatedAt         apijson.Field
	UseProxyConfig    apijson.Field
	UserAgent         apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileGetResponse) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseJSON) RawJSON() string {
	return r.raw
}

// The dimensions associated with the profile
type ProfileGetResponseDimensions struct {
	Height float64                          `json:"height" api:"required"`
	Width  float64                          `json:"width" api:"required"`
	JSON   profileGetResponseDimensionsJSON `json:"-"`
}

// profileGetResponseDimensionsJSON contains the JSON metadata for the struct
// [ProfileGetResponseDimensions]
type profileGetResponseDimensionsJSON struct {
	Height      apijson.Field
	Width       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseDimensions) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseDimensionsJSON) RawJSON() string {
	return r.raw
}

// The fingerprint associated with the profile
type ProfileGetResponseFingerprint struct {
	Fingerprint ProfileGetResponseFingerprintFingerprint `json:"fingerprint" api:"required"`
	Headers     ProfileGetResponseFingerprintHeaders     `json:"headers" api:"required"`
	JSON        profileGetResponseFingerprintJSON        `json:"-"`
}

// profileGetResponseFingerprintJSON contains the JSON metadata for the struct
// [ProfileGetResponseFingerprint]
type profileGetResponseFingerprintJSON struct {
	Fingerprint apijson.Field
	Headers     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprint struct {
	AudioCodecs       map[string]string                                         `json:"audioCodecs" api:"required"`
	Battery           ProfileGetResponseFingerprintFingerprintBattery           `json:"battery" api:"required"`
	Fonts             []string                                                  `json:"fonts" api:"required"`
	MockWebRtc        bool                                                      `json:"mockWebRTC" api:"required,nullable"`
	MultimediaDevices ProfileGetResponseFingerprintFingerprintMultimediaDevices `json:"multimediaDevices" api:"required"`
	Navigator         ProfileGetResponseFingerprintFingerprintNavigator         `json:"navigator" api:"required"`
	PluginsData       ProfileGetResponseFingerprintFingerprintPluginsData       `json:"pluginsData" api:"required"`
	Screen            ProfileGetResponseFingerprintFingerprintScreen            `json:"screen" api:"required"`
	Slim              bool                                                      `json:"slim" api:"required,nullable"`
	VideoCard         ProfileGetResponseFingerprintFingerprintVideoCard         `json:"videoCard" api:"required"`
	VideoCodecs       map[string]string                                         `json:"videoCodecs" api:"required"`
	JSON              profileGetResponseFingerprintFingerprintJSON              `json:"-"`
}

// profileGetResponseFingerprintFingerprintJSON contains the JSON metadata for
// the struct [ProfileGetResponseFingerprintFingerprint]
type profileGetResponseFingerprintFingerprintJSON struct {
	AudioCodecs       apijson.Field
	Battery           apijson.Field
	Fonts             apijson.Field
	MockWebRtc        apijson.Field
	MultimediaDevices apijson.Field
	Navigator         apijson.Field
	PluginsData       apijson.Field
	Screen            apijson.Field
	Slim              apijson.Field
	VideoCard         apijson.Field
	VideoCodecs       apijson.Field
	raw               string
	ExtraFields       map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprint) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintBattery struct {
	Charging        bool                                                `json:"charging" api:"required,nullable"`
	ChargingTime    float64                                             `json:"chargingTime" api:"required,nullable"`
	DischargingTime float64                                             `json:"dischargingTime" api:"required,nullable"`
	Level           float64                                             `json:"level" api:"required,nullable"`
	JSON            profileGetResponseFingerprintFingerprintBatteryJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintBatteryJSON contains the JSON
// metadata for the struct [ProfileGetResponseFingerprintFingerprintBattery]
type profileGetResponseFingerprintFingerprintBatteryJSON struct {
	Charging        apijson.Field
	ChargingTime    apijson.Field
	DischargingTime apijson.Field
	Level           apijson.Field
	raw             string
	ExtraFields     map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintBattery) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintBatteryJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintMultimediaDevices struct {
	Micros   []ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro   `json:"micros" api:"required"`
	Speakers []ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker `json:"speakers" api:"required"`
	Webcams  []ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam  `json:"webcams" api:"required"`
	JSON     profileGetResponseFingerprintFingerprintMultimediaDevicesJSON      `json:"-"`
}

// profileGetResponseFingerprintFingerprintMultimediaDevicesJSON contains the
// JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintMultimediaDevices]
type profileGetResponseFingerprintFingerprintMultimediaDevicesJSON struct {
	Micros      apijson.Field
	Speakers    apijson.Field
	Webcams     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintMultimediaDevices) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintMultimediaDevicesJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro struct {
	DeviceID string                                                             `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                             `json:"groupId" api:"required,nullable"`
	Kind     string                                                             `json:"kind" api:"required,nullable"`
	Label    string                                                             `json:"label" api:"required,nullable"`
	JSON     profileGetResponseFingerprintFingerprintMultimediaDevicesMicroJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintMultimediaDevicesMicroJSON contains
// the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro]
type profileGetResponseFingerprintFingerprintMultimediaDevicesMicroJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintMultimediaDevicesMicro) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintMultimediaDevicesMicroJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker struct {
	DeviceID string                                                               `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                               `json:"groupId" api:"required,nullable"`
	Kind     string                                                               `json:"kind" api:"required,nullable"`
	Label    string                                                               `json:"label" api:"required,nullable"`
	JSON     profileGetResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON contains
// the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker]
type profileGetResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintMultimediaDevicesSpeaker) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintMultimediaDevicesSpeakerJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam struct {
	DeviceID string                                                              `json:"deviceId" api:"required,nullable"`
	GroupID  string                                                              `json:"groupId" api:"required,nullable"`
	Kind     string                                                              `json:"kind" api:"required,nullable"`
	Label    string                                                              `json:"label" api:"required,nullable"`
	JSON     profileGetResponseFingerprintFingerprintMultimediaDevicesWebcamJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintMultimediaDevicesWebcamJSON contains
// the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam]
type profileGetResponseFingerprintFingerprintMultimediaDevicesWebcamJSON struct {
	DeviceID    apijson.Field
	GroupID     apijson.Field
	Kind        apijson.Field
	Label       apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintMultimediaDevicesWebcam) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintMultimediaDevicesWebcamJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintNavigator struct {
	AppCodeName         string                                                           `json:"appCodeName" api:"required,nullable"`
	AppName             string                                                           `json:"appName" api:"required,nullable"`
	AppVersion          string                                                           `json:"appVersion" api:"required,nullable"`
	DeviceMemory        float64                                                          `json:"deviceMemory" api:"required,nullable"`
	ExtraProperties     ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties `json:"extraProperties" api:"required"`
	HardwareConcurrency float64                                                          `json:"hardwareConcurrency" api:"required,nullable"`
	Language            string                                                           `json:"language" api:"required,nullable"`
	Languages           []string                                                         `json:"languages" api:"required"`
	MaxTouchPoints      float64                                                          `json:"maxTouchPoints" api:"required,nullable"`
	Oscpu               string                                                           `json:"oscpu" api:"required,nullable"`
	Platform            string                                                           `json:"platform" api:"required,nullable"`
	Product             string                                                           `json:"product" api:"required,nullable"`
	ProductSub          string                                                           `json:"productSub" api:"required,nullable"`
	UserAgent           string                                                           `json:"userAgent" api:"required,nullable"`
	UserAgentData       ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData   `json:"userAgentData" api:"required"`
	Vendor              string                                                           `json:"vendor" api:"required,nullable"`
	VendorSub           string                                                           `json:"vendorSub" api:"required,nullable"`
	Webdriver           bool                                                             `json:"webdriver" api:"required,nullable"`
	DoNotTrack          string                                                           `json:"doNotTrack" api:"nullable"`
	JSON                profileGetResponseFingerprintFingerprintNavigatorJSON            `json:"-"`
}

// profileGetResponseFingerprintFingerprintNavigatorJSON contains the JSON
// metadata for the struct [ProfileGetResponseFingerprintFingerprintNavigator]
type profileGetResponseFingerprintFingerprintNavigatorJSON struct {
	AppCodeName         apijson.Field
	AppName             apijson.Field
	AppVersion          apijson.Field
	DeviceMemory        apijson.Field
	ExtraProperties     apijson.Field
	HardwareConcurrency apijson.Field
	Language            apijson.Field
	Languages           apijson.Field
	MaxTouchPoints      apijson.Field
	Oscpu               apijson.Field
	Platform            apijson.Field
	Product             apijson.Field
	ProductSub          apijson.Field
	UserAgent           apijson.Field
	UserAgentData       apijson.Field
	Vendor              apijson.Field
	VendorSub           apijson.Field
	Webdriver           apijson.Field
	DoNotTrack          apijson.Field
	raw                 string
	ExtraFields         map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintNavigator) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintNavigatorJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties struct {
	GlobalPrivacyControl bool                                                                 `json:"globalPrivacyControl" api:"required,nullable"`
	InstalledApps        []string                                                             `json:"installedApps" api:"required"`
	PdfViewerEnabled     bool                                                                 `json:"pdfViewerEnabled" api:"required,nullable"`
	VendorFlavors        []string                                                             `json:"vendorFlavors" api:"required"`
	JSON                 profileGetResponseFingerprintFingerprintNavigatorExtraPropertiesJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintNavigatorExtraPropertiesJSON contains
// the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties]
type profileGetResponseFingerprintFingerprintNavigatorExtraPropertiesJSON struct {
	GlobalPrivacyControl apijson.Field
	InstalledApps        apijson.Field
	PdfViewerEnabled     apijson.Field
	VendorFlavors        apijson.Field
	raw                  string
	ExtraFields          map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintNavigatorExtraProperties) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintNavigatorExtraPropertiesJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData struct {
	Brands   []ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand `json:"brands" api:"required"`
	Mobile   bool                                                                  `json:"mobile" api:"required,nullable"`
	Platform string                                                                `json:"platform" api:"required,nullable"`
	JSON     profileGetResponseFingerprintFingerprintNavigatorUserAgentDataJSON    `json:"-"`
}

// profileGetResponseFingerprintFingerprintNavigatorUserAgentDataJSON contains
// the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData]
type profileGetResponseFingerprintFingerprintNavigatorUserAgentDataJSON struct {
	Brands      apijson.Field
	Mobile      apijson.Field
	Platform    apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintNavigatorUserAgentData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintNavigatorUserAgentDataJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand struct {
	Brand   string                                                                  `json:"brand" api:"required,nullable"`
	Version string                                                                  `json:"version" api:"required,nullable"`
	JSON    profileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON
// contains the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand]
type profileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON struct {
	Brand       apijson.Field
	Version     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrand) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintNavigatorUserAgentDataBrandJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintPluginsData struct {
	MimeTypes []string                                                    `json:"mimeTypes" api:"required"`
	Plugins   []ProfileGetResponseFingerprintFingerprintPluginsDataPlugin `json:"plugins" api:"required"`
	JSON      profileGetResponseFingerprintFingerprintPluginsDataJSON     `json:"-"`
}

// profileGetResponseFingerprintFingerprintPluginsDataJSON contains the JSON
// metadata for the struct [ProfileGetResponseFingerprintFingerprintPluginsData]
type profileGetResponseFingerprintFingerprintPluginsDataJSON struct {
	MimeTypes   apijson.Field
	Plugins     apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintPluginsData) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintPluginsDataJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintPluginsDataPlugin struct {
	Description string                                                              `json:"description" api:"required,nullable"`
	Filename    string                                                              `json:"filename" api:"required,nullable"`
	MimeTypes   []ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType `json:"mimeTypes" api:"required"`
	Name        string                                                              `json:"name" api:"required,nullable"`
	JSON        profileGetResponseFingerprintFingerprintPluginsDataPluginJSON       `json:"-"`
}

// profileGetResponseFingerprintFingerprintPluginsDataPluginJSON contains the
// JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintPluginsDataPlugin]
type profileGetResponseFingerprintFingerprintPluginsDataPluginJSON struct {
	Description apijson.Field
	Filename    apijson.Field
	MimeTypes   apijson.Field
	Name        apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintPluginsDataPlugin) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintPluginsDataPluginJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType struct {
	Description   string                                                                `json:"description" api:"required,nullable"`
	EnabledPlugin string                                                                `json:"enabledPlugin" api:"required,nullable"`
	Suffixes      string                                                                `json:"suffixes" api:"required,nullable"`
	Type          string                                                                `json:"type" api:"required,nullable"`
	JSON          profileGetResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON
// contains the JSON metadata for the struct
// [ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType]
type profileGetResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON struct {
	Description   apijson.Field
	EnabledPlugin apijson.Field
	Suffixes      apijson.Field
	Type          apijson.Field
	raw           string
	ExtraFields   map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintPluginsDataPluginMimeType) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintPluginsDataPluginMimeTypeJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintScreen struct {
	AvailHeight      float64                                            `json:"availHeight" api:"required,nullable"`
	AvailLeft        float64                                            `json:"availLeft" api:"required,nullable"`
	AvailTop         float64                                            `json:"availTop" api:"required,nullable"`
	AvailWidth       float64                                            `json:"availWidth" api:"required,nullable"`
	ClientHeight     float64                                            `json:"clientHeight" api:"required,nullable"`
	ClientWidth      float64                                            `json:"clientWidth" api:"required,nullable"`
	ColorDepth       float64                                            `json:"colorDepth" api:"required,nullable"`
	DevicePixelRatio float64                                            `json:"devicePixelRatio" api:"required,nullable"`
	HasHdr           bool                                               `json:"hasHDR" api:"required,nullable"`
	Height           float64                                            `json:"height" api:"required,nullable"`
	InnerHeight      float64                                            `json:"innerHeight" api:"required,nullable"`
	InnerWidth       float64                                            `json:"innerWidth" api:"required,nullable"`
	OuterHeight      float64                                            `json:"outerHeight" api:"required,nullable"`
	OuterWidth       float64                                            `json:"outerWidth" api:"required,nullable"`
	PageXOffset      float64                                            `json:"pageXOffset" api:"required,nullable"`
	PageYOffset      float64                                            `json:"pageYOffset" api:"required,nullable"`
	PixelDepth       float64                                            `json:"pixelDepth" api:"required,nullable"`
	ScreenX          float64                                            `json:"screenX" api:"required,nullable"`
	Width            float64                                            `json:"width" api:"required,nullable"`
	JSON             profileGetResponseFingerprintFingerprintScreenJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintScreenJSON contains the JSON metadata
// for the struct [ProfileGetResponseFingerprintFingerprintScreen]
type profileGetResponseFingerprintFingerprintScreenJSON struct {
	AvailHeight      apijson.Field
	AvailLeft        apijson.Field
	AvailTop         apijson.Field
	AvailWidth       apijson.Field
	ClientHeight     apijson.Field
	ClientWidth      apijson.Field
	ColorDepth       apijson.Field
	DevicePixelRatio apijson.Field
	HasHdr           apijson.Field
	Height           apijson.Field
	InnerHeight      apijson.Field
	InnerWidth       apijson.Field
	OuterHeight      apijson.Field
	OuterWidth       apijson.Field
	PageXOffset      apijson.Field
	PageYOffset      apijson.Field
	PixelDepth       apijson.Field
	ScreenX          apijson.Field
	Width            apijson.Field
	raw              string
	ExtraFields      map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintScreen) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintScreenJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintFingerprintVideoCard struct {
	Renderer string                                                `json:"renderer" api:"required,nullable"`
	Vendor   string                                                `json:"vendor" api:"required,nullable"`
	JSON     profileGetResponseFingerprintFingerprintVideoCardJSON `json:"-"`
}

// profileGetResponseFingerprintFingerprintVideoCardJSON contains the JSON
// metadata for the struct [ProfileGetResponseFingerprintFingerprintVideoCard]
type profileGetResponseFingerprintFingerprintVideoCardJSON struct {
	Renderer    apijson.Field
	Vendor      apijson.Field
	raw         string
	ExtraFields map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintFingerprintVideoCard) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintFingerprintVideoCardJSON) RawJSON() string {
	return r.raw
}

type ProfileGetResponseFingerprintHeaders struct {
	UserAgent               string                                   `json:"user-agent" api:"required,nullable"`
	Accept                  string                                   `json:"accept" api:"nullable"`
	AcceptEncoding          string                                   `json:"accept-encoding" api:"nullable"`
	AcceptLanguage          string                                   `json:"accept-language" api:"nullable"`
	Dnt                     string                                   `json:"dnt" api:"nullable"`
	SecChUa                 string                                   `json:"sec-ch-ua" api:"nullable"`
	SecChUaMobile           string                                   `json:"sec-ch-ua-mobile" api:"nullable"`
	SecChUaPlatform         string                                   `json:"sec-ch-ua-platform" api:"nullable"`
	SecFetchDest            string                                   `json:"sec-fetch-dest" api:"nullable"`
	SecFetchMode            string                                   `json:"sec-fetch-mode" api:"nullable"`
	SecFetchSite            string                                   `json:"sec-fetch-site" api:"nullable"`
	SecFetchUser            string                                   `json:"sec-fetch-user" api:"nullable"`
	UpgradeInsecureRequests string                                   `json:"upgrade-insecure-requests" api:"nullable"`
	JSON                    profileGetResponseFingerprintHeadersJSON `json:"-"`
}

// profileGetResponseFingerprintHeadersJSON contains the JSON metadata for the
// struct [ProfileGetResponseFingerprintHeaders]
type profileGetResponseFingerprintHeadersJSON struct {
	UserAgent               apijson.Field
	Accept                  apijson.Field
	AcceptEncoding          apijson.Field
	AcceptLanguage          apijson.Field
	Dnt                     apijson.Field
	SecChUa                 apijson.Field
	SecChUaMobile           apijson.Field
	SecChUaPlatform         apijson.Field
	SecFetchDest            apijson.Field
	SecFetchMode            apijson.Field
	SecFetchSite            apijson.Field
	SecFetchUser            apijson.Field
	UpgradeInsecureRequests apijson.Field
	raw                     string
	ExtraFields             map[string]apijson.Field
}

func (r *ProfileGetResponseFingerprintHeaders) UnmarshalJSON(data []byte) (err error) {
	return apijson.UnmarshalRoot(data, r)
}

func (r profileGetResponseFingerprintHeadersJSON) RawJSON() string {
	return r.raw
}

// The status of the profile
type ProfileGetResponseStatus string

const (
	ProfileGetResponseStatusUploading ProfileGetResponseStatus = "UPLOADING"
	ProfileGetResponseStatusReady     ProfileGetResponseStatus = "READY"
	ProfileGetResponseStatusFailed    ProfileGetResponseStatus = "FAILED"
)

func (r ProfileGetResponseStatus) IsKnown() bool {
	switch r {
	case ProfileGetResponseStatusUploading, ProfileGetResponseStatusReady, ProfileGetResponseStatusFailed:
		return true
	}
	return false
}
