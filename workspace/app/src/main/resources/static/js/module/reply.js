// reply.js는 모듈을 만들어 두는 파일이다.
// 자바스크립트의 함수, 클래스를 모듈화 시켜 저장할 수 있다.
// 우리는 함수를 부품으로 만들어두고 다른 파일에서 사용할 것이다.
// 이 모듈들을 밖에서 사용할 수 있도록 내보내는 키워드가 export이다.

export function add(){}

export function getList(boardNumber, callback, error){
    $.ajax({
        url : `/replies/list/${boardNumber}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : error
    });
}
export function getView(){}
export function modify() {}
export function remove(){}