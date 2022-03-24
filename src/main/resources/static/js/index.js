$(function(){
  let todayDate = new Date();
  let year = todayDate.getFullYear();
  let month = todayDate.getMonth()+1;

  if(month<10){
    month="0"+month;
  }

  let url = document.location.href;

  let questionMark = url.indexOf("?");
  if(questionMark==-1){
    location.href="/index?date="+year+"-"+month+"&member=all";
  }

  /*
  var para = url.substr(url.indexOf("?")+1);
  var details = para.split("&");
  var dept_no = details[0].substr(details[0].indexOf("=")+1);
  var teacher_id = details[1].substr(details[1].indexOf("=")+1);
  */

  /*func_getTodaySchedule(year+"-"+month+"-"+date);*/

});


// 오늘 일정 가져오기
function func_getTodaySchedule(todayDate){
  $.ajax({
    url:"/getTodaySchedule",
    type: "post",
    dataType: "json",
    data:{todayDate:todayDate},
    success: function(data){
      let cnt = data.scheduleList.length;

      if(cnt>0) {    // 해당하는 일정이 있는 경우
        $.each(data.scheduleList, function (idx,val) {
          let scheduleArray = val.split(",");
          let length = scheduleArray.length;

          if($("#loginType").val()=="1"){
            func_requestCntDelete();  // 요청인원 지우기

            for(let i=4; i<length; i++){
              if(i==4){
                attenderArray=scheduleArray[i];
              } else {
                attenderArray+=","+scheduleArray[i];
              }
            }
            attenderCnt = attenderArray.split(",").length;
            if(scheduleArray[4]==""){
              attenderCnt = 0;
            }

            $(".indexSchedule").append("<p class='indexContentsDetail detail2' value='"+scheduleArray[0]+"'> "
                + "<span class='indexContentsDetailInfo2'>"+scheduleArray[1]+"</span>"
                + "<span class='noticeSubject1'>검사요청 인원: "
                + "<span class='import'>"+attenderCnt+"</span>명</span></p>");
          }
          else {
            $(".indexSchedule").append("<p class='indexContentsDetail detail2' value='"+scheduleArray[0]+"'> "
                + "<span class='indexContentsDetailInfo2'>"+scheduleArray[1]+"</span>"
                + "<span class='noticeSubject1'>"
                + "<span>"+scheduleArray[2]+" "+scheduleArray[3]+" "+scheduleArray[4]+"</span></span></p>");
          }
        });
      }
      else{    // 해당하는 일정이 없는 경우
        if($("#loginType").val()=="1"){
          $(".indexSchedule").append("<p class='indexContentsDetail detail2'> "
              + "<span class='noticeSubject2'>오늘 일정은 없습니다.</span></p>");
          func_requestCnt();  // 교수님의 경우, 개설요청인원 표기하기
        }
        else {
          $(".indexSchedule").append("<p class='indexContentsDetail detail2'> "
              + "<span class='noticeSubject1'>오늘 일정은 없습니다.</span></p>");
        }
      }
    },
    error: function(report, status, error){
      alert("code: "+report.status+"\n"+"message: "+report.responseText+"\n"+"error: "+error);
    }
  });
}
