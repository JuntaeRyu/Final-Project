(function($) {
	document.addEventListener('DOMContentLoaded', function() {
    var elements = [
        document.getElementById('option1'),
        document.getElementById('option2'),
        document.getElementById('option3'),
        document.getElementById('option4')
    ];
    
    // 페이지 로딩 완료 후 클래스 제거
    elements.forEach(function(element) {
        element.classList.remove('current');
    });
    
    // 페이지 경로에 따른 조건 확인 및 클래스 추가
    if (window.location.pathname === '/app/main.jsp') {
        elements[0].classList.add('current');
    } else if (window.location.pathname === '/app/noticeListPage.do') {
        elements[1].classList.add('current');
    } else if (window.location.pathname === '/app/boardListPage.do') {
        elements[2].classList.add('current');
    } else if (window.location.pathname === '/app/infoListPage.do') {
        elements[2].classList.add('current');
    } else if (window.location.pathname === '/app/chatListPage.do') {
        elements[2].classList.add('current');
    } else if (window.location.pathname === '/app/memberListPage.do') {
        elements[3].classList.add('current');
    } else {
        elements.forEach(function(element) {
            element.classList.add('default-class');
        });
    }
});

})(jQuery);