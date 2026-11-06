// File generated from our OpenAPI spec. See CONTRIBUTING.md for details.

package shared

type UnionString string

func (UnionString) ImplementsCommandFrameUnionParam()                                {}
func (UnionString) ImplementsCommandSelectValuesUnionParam()                         {}
func (UnionString) ImplementsCommandTargetUnionParam()                               {}
func (UnionString) ImplementsCommandUploadFilesUnionParam()                          {}
func (UnionString) ImplementsSessionCommandsParamsCommandsSelectCommandValuesUnion() {}
func (UnionString) ImplementsSessionCommandsParamsCommandsUploadCommandFilesUnion()  {}
func (UnionString) ImplementsSessionNewParamsMetadataUnion()                         {}
func (UnionString) ImplementsSessionReplaceMetadataParamsMetadataUnion()             {}
func (UnionString) ImplementsSessionUpdateMetadataParamsMetadataUnion()              {}

type UnionBool bool

func (UnionBool) ImplementsSessionNewParamsMetadataUnion()             {}
func (UnionBool) ImplementsSessionNewParamsOptimizeBandwidthUnion()    {}
func (UnionBool) ImplementsSessionReplaceMetadataParamsMetadataUnion() {}
func (UnionBool) ImplementsSessionUpdateMetadataParamsMetadataUnion()  {}

type UnionFloat float64

func (UnionFloat) ImplementsSessionNewParamsMetadataUnion()             {}
func (UnionFloat) ImplementsSessionReplaceMetadataParamsMetadataUnion() {}
func (UnionFloat) ImplementsSessionUpdateMetadataParamsMetadataUnion()  {}
