import * as reply from '../module/reply.js';
//모듈 경로는 일반적으로 상대경로로 접근한다.
const boardNumber = $('.board-num').val();
reply.getList(boardNumber, showReply, showError);

function showReply(replies){
    // console.log(result);
    let text = '';

    replies.forEach(r =>{
        text+=`
            <div class="reply" data-num="${r.replyNumber}">
                    <div class="reply-box">
                        <div class="reply-box__writer">${r.userId}</div>
                        <div class="reply-box__content">${r.replyContent}</div>
                    </div>
                    <div class="reply-btn-box">
                `;

        if(r.userNumber==loginNumber){
            text+=`
                        <span class="reply-btns"></span>
                        <div class="reply-btns__box none">
                            <div class="reply-remove-btn">삭제</div>
                            <div class="reply-modify-btn">수정</div>
                        </div>`;
            }
            text+=`
                    </div>
                </div>
        `;

    });
    $('.reply-list-wrap').html(text);

}

function showError(a, b, c){
    console.error(c);
}


$('.reply-list-wrap').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');

    $replyBtnBox.toggleClass('none');
});

$('body').click(function (e) {
    if (e.target == $('.reply-btns')[0]) {
        console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});

// 삭제 버튼
$('.btn-remove').on('click', function(){
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/remove?boardNumber=' + boardNumber;

//    참고 : post로 요청보내기
//     let obj = document.createElement('input');
//     obj.setAttribute('type', 'hidden');
//     obj.setAttribute('name', 'boardNumber');
//     obj.setAttribute('value', boardNumber);
//
//     let f = document.createElement('form');
//     f.appendChild(obj);
//     f.setAttribute('method', 'post');
//     f.setAttribute('action', '/board/remove')
//     document.body.appendChild(f);
//     f.submit();
});

// 수정 버튼
$('.btn-modify').on('click', function(){
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/modify?boardNumber=' + boardNumber;
});

//뒤로가기 버튼
$('.btn-back').on('click', function (){
    window.location.href = '/board/list';
})

//이미지 처리
displayAjax();

function displayAjax(){
    let boardNumber = $('.board-num').val();

    $.ajax({
        url : '/files/imgList',
        type : 'get',
        data : {boardNumber : boardNumber},
        success : function(files){
            let text = '';

            files.forEach(file => {
                let fileName = file.fileUploadPath + '/' + file.fileUuid + '_' + file.fileName;
                text += `
                    <a href="/files/download?fileName=${fileName}">
                      <img src="/files/display?fileName=${fileName}" data-number="${file.fileNumber}" data-name="${fileName}" />
                    </a>
`;
            });

            $('.post-images').html(text);
        }
    });
}


// 리플 작성 완료 처리
$('.btn-reply').on('click', function (){

});

// 리플 삭제 버튼 처리
$('.reply-list-wrap').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');
});

// 리플 수정 버튼 처리
$('.reply-list-wrap').on('click', '.reply-modify-btn', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
  <div class='modify-box'>
    <textarea class='modify-content'>${$content.text()}</textarea>
    <button type='button' class='modify-content-btn'>수정 완료</button>
  </div>
  `);
    $('.reply-btns__box').addClass('none');
});

// 리플 수정 완료 처리
$('.reply-list-wrap').on('click', '.modify-content-btn', function () {
    console.log('modify!!!');
});