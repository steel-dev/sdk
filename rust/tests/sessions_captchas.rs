mod common;

#[tokio::test]
async fn solve() {
    let client = common::client();
    let result = client
        .sessions()
        .captchas()
        .solve(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionCaptchaSolveParams {
                page_id: Some("pageId".to_owned()),
                task_id: Some("taskId".to_owned()),
                url: Some("url".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn solve_image() {
    let client = common::client();
    let result = client
        .sessions()
        .captchas()
        .solve_image(
            "182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e",
            steel_rs::SessionCaptchaSolveImageParams {
                image_x_path: "imageXPath".to_owned(),
                input_x_path: "inputXPath".to_owned(),
                url: Some("url".to_owned()),
            },
        )
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}

#[tokio::test]
async fn status() {
    let client = common::client();
    let result = client
        .sessions()
        .captchas()
        .status("182bd5e5-6e1a-4fe4-a799-aa6d9a6ab26e")
        .await;
    assert!(result.is_ok(), "{:?}", result.err());
}
