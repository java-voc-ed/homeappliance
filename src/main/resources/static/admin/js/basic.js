//static
const kCurrentLoginEmployeKey = 'currentLoginEmploye';
const kLoginPath = '/admin/singIn/index.html';
const kIndexPath = '/admin/index.html';

/*
Methods
*/
//檢查是否登入若沒有登入就導到登入頁
function checkEmployeDidLogingAndReplaceURL() {
    if (getCurrentLoginEmploye()==null) {
        window.location.replace(kLoginPath);
    }
}

function getCurrentLoginEmploye(){
    var currentLoginEmploye;
    var getCurrentLoginEmployeJsonString = window.sessionStorage.getItem(kCurrentLoginEmployeKey);
    if (getCurrentLoginEmployeJsonString) {
        currentLoginEmploye = JSON.parse(getCurrentLoginEmployeJsonString);
    }
    return currentLoginEmploye;
}


//存資料（導頁）
function saveCurrentLoginEmploye(jsonString) {
    window.sessionStorage.setItem(kCurrentLoginEmployeKey,jsonString)
    window.location.replace(kIndexPath);
}

//登出 (清資料,導頁)
function logoutCurrentLoginEmploye(){
    window.sessionStorage.removeItem(kCurrentLoginEmployeKey);
    window.location.replace(kLoginPath);
}


function showMessage(message) {
    alert(message);
}



/*
LifeCycel
*/
// $(document).ready(function () {
//      checkEmployeDidLogingAndReplaceURL();
// });