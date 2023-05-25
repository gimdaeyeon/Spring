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
//게시글 수정
$('.btn-modify').on('click', function (){
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/modify?boardNumber='+boardNumber;
})
//게시글 삭제
$('.btn-remove').on('click', function (){
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/remove?boardNumber='+boardNumber;
})

//뒤로가기 버튼
$('.btn-back').on('click', function (){
    window.location.href = '/board/list';
})

displayAjax();
function displayAjax(){
    let boardNumber = $('.board-num').val();
    $.ajax({
        url: '/files/imgList',
        type: 'get',
        data: {boardNumber:boardNumber},
        success : function (files){
            let text = '';

            files.forEach(file=>{
                let fileName = file.fileUploadPath+'/'+file.fileUuid+'_'+file.fileName;
                text+=`
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