function appendItem() {
  $("#list_append_item").append('<div class="border_control append_item"><div style="width: 37%" class="group_item"><label class="sub_label">날짜 </label><div class="sub_input"> <input name="test138" type="text" data-date-format="yyyy/mm/dd" value="2016/08/23" class="form-control input-small date-picker"/> </div> </div> <div class="group_item"> <label class="sub_label">시간 </label> <div class="sub_input"> <div class="item_time"> <input type="text" name="from" style="width: 100px;" class="form-control"/> </div> <div class="item_time"> <span class="input-group-addon">~ </span></div> <div class="item_time"> <input type="text" name="from" style="width: 100px;" class="form-control"/> </div> </div> </div>  <div class="trash group_item"> <a href="javascript:;" class="trash_icon detele_item"> <i class="fa fa-remove"></i></a></div> </div>');     // Append new elements
}
$(document).ready(function() {
  // $(this).attr("href");
  
  (function($) {
    function brelink() {
      if ($('.side-menu .current-page').length) {
        var titlefirst = $('.side-menu li.active > a').text();
        var titlelast = $('.side-menu .current-page a').text();
        var titleAddition =  $(".page-breadcrumb").html();
        var pram = window.location.pathname;
        if (pram =='/auth/user/member/create') {
        	titlelast = "후원회원 등록";
		}
        
        $(".page-breadcrumb").empty();
        $(".page-breadcrumb").append('<li><a href="/auth/campaign" class="home"> <i class="fa fa-home"></i><span>홈</span></a></li>');
        if(titlefirst != ''){
        	$(".page-breadcrumb").append('<li class="menu-lever1"><a>'+(titlefirst)+'</a></li>');
        }
        if(titlelast != ''){
        	$(".page-breadcrumb").append('<li class="menu-lever2"><a>'+(titlelast)+'</a></li>');
        }
        $(".page-breadcrumb").append(titleAddition);
        // $("body").addClass('fix-addtocat');
      }
    }

    $(window).on('load', function() {
      brelink();
    });
  })(jQuery);

  $('.last-checkbox').change(function(){
    if($('.last-checkbox').is(":checked")) {
      $('.list-sub-checkbox').show(300);
    }
    else{
      $('.list-sub-checkbox').hide(300);
    }
  });

  $(document.body).on('click', '.detele_item', function() {
    $(this).closest('.append_item').fadeOut(300, function() {
      $(this).remove();
    });
  });/* --------------------------------------------------------------------- */
  /* Delete item
  /* --------------------------------------------------------------------- */

  $(document.body).on('click', '.btn_delete', function() {
    $(this).closest('.item-comment').fadeOut(300, function() {
      $(this).remove();
    });
  });
  
  /* --------------------------------------------------------------------- */
  /* Metronic
  /* --------------------------------------------------------------------- */
  (function($) {
    if ($("body.login-page").length) return;

    Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features
    // Calendar.init();
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* data-rw-placeholder
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$("[data-rw-placeholder]").length) return;

    $("[data-rw-placeholder]").each(function() {
      var placeholderContent = $(this).attr("data-rw-placeholder");
      $(this).attr("placeholder", placeholderContent);

      $(this).on("focus", function() {
        $(this).attr("placeholder", "");
      });

      $(this).on("blur", function() {
        $(this).attr("placeholder", placeholderContent);
      });
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .date-picker
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".date-picker").length) return;

    $('.date-picker').datepicker({
      rtl: Metronic.isRTL(),
      orientation: "left",
      autoclose: true,
      language: 'kr'
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .ckeditor
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".ckeditor").length) return;

    var idNum = 1;

    $(".ckeditor").each(function() {
      $(this).attr("id", "sb-ckeditor" + idNum);
      var id = $(this).attr("id");

      $("" + id + "").ckeditor({
        language: "ko"
      });

      idNum++;
    });
  })(jQuery);


  /* --------------------------------------------------------------------- */
  /* .check-all-wrapper
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".check-all-wrapper").length) return;

    $(document.body).on("change", ".check-all", function() {
      var checkAll = $(this);
      var wrapper = $(this).closest(".check-all-wrapper");
      var item = wrapper.find(".check-item");

      checkAll.prop('checked') == true ? item.prop('checked', true) : item.prop('checked', false)

      $.uniform.update();
    });

    $(document.body).on("change", ".check-item", function() {
      var item = $(this);
      var wrapper = $(this).closest(".check-all-wrapper");
      var checkAll = wrapper.find(".check-all");
      var countItem = wrapper.find(".check-item:not(':checked')").length;

      countItem === 0 ? checkAll.prop('checked', true) : checkAll.prop('checked', false)

      $.uniform.update();
    });

  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .check-all-wrapper
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".rw-toggle-checkbox").length) return;

    $(".rw-toggle-checkbox").each(function() {
      var checkbox = $(this).find(".toggle-checkbox");
      var content = $(this).find(".toggle-content");

      checkbox.prop('checked') == true ? content.show() : content.hide()
    });

    $(document.body).on("change", ".toggle-checkbox", function() {
      var checkbox = $(this);
      var wrapper = $(this).closest(".rw-toggle-checkbox");
      var content = wrapper.find(".toggle-content");

      checkbox.prop('checked') == true ? content.show() : content.hide()

      $.uniform.update();
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .sortable
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".sortable").length) return;

    $(".sortable").each(function() {
      $(this).sortable({
        cursor: "move",
        update: function(event, ui) {
          var changedList = this.id;
          var order = $(this).sortable('toArray');
          var positions = order.join(';');

          alert("PLEASE VIEW CONSOLE.LOG");

          console.log({
            id: changedList,
            positions: positions
          });
        }
      });

      $(this).disableSelection();
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  (function() {
    if (!$('.rw-file-upload.image-upload').length) return;

    $('.rw-file-upload.image-upload').each(function() {
      var fileUpload = $(this);

      var readURL = function(input) {
        var previewImage = fileUpload.find(".preview-image img");

        if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function(e) {
            previewImage.attr('src', e.target.result);
          }

          reader.readAsDataURL(input.files[0]);
        }
      };

      fileUpload.find(".input-file").on("change", function() {
        readURL(this);
      });
    });
  })();

  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  (function($) {
    function inputuploadimg() {
        if (!$('.tr-file-upload.image-upload').length) return;

        $('.tr-file-upload.image-upload').each(function() {
          var fileUpload = $(this);
          var previewImage = fileUpload.find('.preview-image > img');
          var previewImageBg = fileUpload.find('.preview-image > .image-bg');
          

          previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
          previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

          var readURL = function(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();

              reader.onload = function(e) {

                previewImage.attr('src', e.target.result);
                previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
              }

              reader.readAsDataURL(input.files[0]);
            }
          };

          fileUpload.find('.input-file').on('change', function() {
            readURL(this);
          });
        });
    }

    $(window).on('load', function() {
      inputuploadimg();
    });
  })(jQuery);

  // all upload file
  (function($) {
    function inputuploadall() {
        if (!$('.all-file-upload.image-upload').length) return;

        $('.tr-file-upload.image-upload').each(function() {
          var fileUpload = $(this);
          // var previewImage = fileUpload.find('.preview-image > img');
          // var previewImageBg = fileUpload.find('.preview-image > .image-bg');
          

          // previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
          // previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

          var readURL = function(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();

              reader.onload = function(e) {

                // previewImage.attr('src', e.target.result);
                // previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
              }

              reader.readAsDataURL(input.files[0]);
            }
          };

          fileUpload.find('.input-file').on('change', function() {
            readURL(this);
          });
        });
    }

    $(window).on('load', function() {
      inputuploadall();
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$('.all-file-upload').length) return;

    var popupUploadAgain = $('#popupUploadAgain');

    $('.all-file-upload').each(function() {
      var fileUploadWrapper = $(this);
      var inputFile = fileUploadWrapper.find('.input-file');
      var borderBox = fileUploadWrapper.find('.border-box');
      var showFileInfo = fileUploadWrapper.find('.show-file-info');
      // var previewImage = fileUploadWrapper.find('.preview-image');
      var deleteButton = fileUploadWrapper.find('.btn-delete');

      inputFile.on('change', function() {
        var thisInputFile = $(this);
        var file = thisInputFile[0].files[0];
        var fileName = file.name;
        var fileSize = file.size;
        showFileInfo.empty().text(fileName);

        if ($('.tr-file-upload.image-upload').length) {
          var capacityLimit = 3 * 1024 * 1024; // = 21MB
          var fileExtension = fileName.split('.').pop().toLowerCase();
          // var listExtensionArray = ['jpg', 'gif', 'png'];
          // var inArrayExtension = $.inArray(fileExtension, listExtensionArray);
          // if(inArrayExtension >= 0){

          // }else{
          //   // alert('파일 형식이 맞지 않습니다.');
          //   // exit();
          // }

          if (fileSize / capacityLimit <= 1) {
            thisInputFile.removeClass('input-empty').addClass('input-not-empty');
            borderBox.css('border', '');
            // previewImage.removeClass('no-image');
          } else {
            // alert('이미지는 3MB 이하만 등록 가능합니다.');
            // exit();
            // thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
            // thisInputFile.removeClass('input-not-empty').addClass('input-empty');
            // borderBox.css('border', '1px solid #ff0000');
            // previewImage.addClass('no-image');
            // showFileInfo.empty();
            // popupUploadAgain.modal('show');
          }
        }

        if (fileUploadWrapper.find('.input-file').val() === '') {
          thisInputFile.addClass('input-empty').removeClass('input-not-empty');
        } else {
          thisInputFile.addClass('input-not-empty').removeClass('input-empty');
        }
      });

      deleteButton.on('click', function() {
        inputFile.replaceWith(inputFile = inputFile.clone(true));
        inputFile.removeClass('input-not-empty').addClass('input-empty');
        borderBox.css('border', '');
        previewImage.addClass('no-image').find('> img').attr('src', '');
        previewImage.addClass('no-image').find('> .image-bg').attr('style', 'background-image: url();');
        showFileInfo.empty();
      });
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  (function($) {
    function inputuploadimg() {
        if (!$('.tr-file-upload.image-upload').length) return;

        $('.tr-file-upload.image-upload').each(function() {
          var fileUpload = $(this);
          var previewImage = fileUpload.find('.preview-image > img');
          var previewImageBg = fileUpload.find('.preview-image > .image-bg');
          

          previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
          previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

          var readURL = function(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();

              reader.onload = function(e) {

                previewImage.attr('src', e.target.result);
                previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
              }

              reader.readAsDataURL(input.files[0]);
            }
          };

          fileUpload.find('.input-file').on('change', function() {
            readURL(this);
          });
        });
    }

    $(window).on('load', function() {
      inputuploadimg();
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$('.tr-file-upload').length) return;

    var popupUploadAgain = $('#popupUploadAgain');

    $('.tr-file-upload').each(function() {
      var fileUploadWrapper = $(this);
      var inputFile = fileUploadWrapper.find('.input-file');
      var borderBox = fileUploadWrapper.find('.border-box');
      var showFileInfo = fileUploadWrapper.find('.show-file-info');
      var previewImage = fileUploadWrapper.find('.preview-image');
      var deleteButton = fileUploadWrapper.find('.btn-delete');

      inputFile.on('change', function() {
        var thisInputFile = $(this);
        var file = thisInputFile[0].files[0];
        var fileName = file.name;
        var fileSize = file.size;
        showFileInfo.empty().text(fileName);

        if ($('.tr-file-upload.image-upload').length) {
          var capacityLimit = 3 * 1024 * 1024; // = 21MB
          var fileExtension = fileName.split('.').pop().toLowerCase();
          var listExtensionArray = ['jpg', 'gif', 'png'];
          var inArrayExtension = $.inArray(fileExtension, listExtensionArray);
          if(inArrayExtension >= 0){

          }else{
            alert('파일 형식이 맞지 않습니다.');
            exit();
          }

          if (inArrayExtension >= 0 && fileSize / capacityLimit <= 1) {
            thisInputFile.removeClass('input-empty').addClass('input-not-empty');
            borderBox.css('border', '');
            previewImage.removeClass('no-image');
          } else {
            alert('이미지는 3MB 이하만 등록 가능합니다.');
            exit();
            thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
            thisInputFile.removeClass('input-not-empty').addClass('input-empty');
            borderBox.css('border', '1px solid #ff0000');
            previewImage.addClass('no-image');
            showFileInfo.empty();
            popupUploadAgain.modal('show');
          }
        }

        if (fileUploadWrapper.find('.input-file').val() === '') {
          thisInputFile.addClass('input-empty').removeClass('input-not-empty');
        } else {
          thisInputFile.addClass('input-not-empty').removeClass('input-empty');
        }
      });

      deleteButton.on('click', function() {
        inputFile.replaceWith(inputFile = inputFile.clone(true));
        inputFile.removeClass('input-not-empty').addClass('input-empty');
        borderBox.css('border', '');
        previewImage.addClass('no-image').find('> img').attr('src', '');
        previewImage.addClass('no-image').find('> .image-bg').attr('style', 'background-image: url();');
        showFileInfo.empty();
      });
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  (function() {
    if (!$('.rw-file-upload').length) return;

    var popupUploadAgain = $('#popupUploadAgain');

    $('.rw-file-upload').each(function() {
      var fileUploadWrapper = $(this);
      var inputFile = fileUploadWrapper.find('.input-file');
      var borderBox = fileUploadWrapper.find('.border-box');
      var showFileInfo = fileUploadWrapper.find('.show-file-info');
      var previewImage = fileUploadWrapper.find('.preview-image');
      var deleteButton = fileUploadWrapper.find('.btn-delete');

      inputFile.on('change', function() {
        var thisInputFile = $(this);
        var file = thisInputFile[0].files[0];
        var fileName = file.name;
        var fileSize = file.size;

        showFileInfo.empty().text(fileName);

        if ($('.rw-file-upload.image-upload').length) {
          var capacityLimit = 21 * 1024 * 1024; // = 8MB
          var fileExtension = fileName.split('.').pop().toLowerCase();
          var listExtensionArray = ["jpg", "jpeg", "gif", "png"];
          var inArrayExtension = $.inArray(fileExtension, listExtensionArray);

          if (inArrayExtension >= 0 && fileSize / capacityLimit <= 1) {
            thisInputFile.removeClass("input-empty").addClass("input-not-empty");
            borderBox.css("border", "");
            previewImage.removeClass("no-image");
          } else {
            thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
            thisInputFile.removeClass("input-not-empty").addClass("input-empty");
            borderBox.css("border", "1px solid #ff0000");
            previewImage.addClass("no-image");
            showFileInfo.empty();
            popupUploadAgain.modal('show');
          }
        }

        if (fileUploadWrapper.find('.input-file').val() === "") {
          thisInputFile.addClass("input-empty").removeClass("input-not-empty");
        } else {
          thisInputFile.addClass("input-not-empty").removeClass("input-empty");
        }
      });

      deleteButton.on('click', function() {
        inputFile.replaceWith(inputFile = inputFile.clone(true));
        inputFile.removeClass("input-not-empty").addClass("input-empty");
        borderBox.css("border", "");
        previewImage.addClass("no-image").find("img").attr("src", "");
        showFileInfo.empty();
      });
    });
  })();



  /* --------------------------------------------------------------------- */
  /* fullCalendar
  /* --------------------------------------------------------------------- */
  (function($) {
    // if (!$("#calendar").length) return;

    if (!jQuery().fullCalendar) return;

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var h = {};

    if (Metronic.isRTL()) {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          right: 'title, prev, next',
          center: '',
          left: 'agendaDay, agendaWeek, month, today'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          right: 'title',
          center: '',
          left: 'agendaDay, agendaWeek, month, today, prev,next'
        };
      }
    } else {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          left: 'title, prev, next',
          center: '',
          right: 'today,month,agendaWeek,agendaDay'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          left: 'title',
          center: '',
          right: 'prev,next,today,month,agendaWeek,agendaDay'
        };
      }
    }

    var initDrag = function(el) {
      // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
      // it doesn't need to have a start or end
      var eventObject = {
        title: $.trim(el.text()) // use the element's text as the event title
      };
      // store the Event Object in the DOM element so we can get to it later
      el.data('eventObject', eventObject);
      // make the event draggable using jQuery UI
      el.draggable({
        zIndex: 999,
        revert: true, // will cause the event to go back to its
        revertDuration: 0 //  original position after the drag
      });
    };

    var addEvent = function(title) {
      title = title.length === 0 ? "Untitled Event" : title;
      var html = $('<div class="external-event label label-default">' + title + '</div>');
      jQuery('#event_box').append(html);
      initDrag(html);
    };

    $('#external-events div.external-event').each(function() {
      initDrag($(this));
    });

    $('#event_add').unbind('click').click(function() {
      var title = $('#event_title').val();
      addEvent(title);
    });

    //predefined events
    $('#event_box').html("");
    addEvent("My Event 1");
    addEvent("My Event 2");
    addEvent("My Event 3");
    addEvent("My Event 4");
    addEvent("My Event 5");
    addEvent("My Event 6");

    $('#calendar').fullCalendar('destroy'); // destroy the calendar
    $('#calendar').fullCalendar({ //re-initialize the calendar
      lang: 'ko', // Customize the language for the calendar.
      header: h,
      defaultView: 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
      slotMinutes: 15,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function(date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.className = $(this).attr("data-class");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }
      },
      events: [{
        title: 'All Day Event',
        start: new Date(y, m, 1),
        backgroundColor: Metronic.getBrandColor('yellow')
      }, {
        title: 'Long Event',
        start: new Date(y, m, d - 5),
        end: new Date(y, m, d - 2),
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d - 3, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('red')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d + 4, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Meeting',
        start: new Date(y, m, d, 10, 30),
        allDay: false,
      }, {
        title: 'Lunch',
        start: new Date(y, m, d, 12, 0),
        end: new Date(y, m, d, 14, 0),
        backgroundColor: Metronic.getBrandColor('grey'),
        allDay: false,
      }, {
        title: 'Birthday Party',
        start: new Date(y, m, d + 1, 19, 0),
        end: new Date(y, m, d + 1, 22, 30),
        backgroundColor: Metronic.getBrandColor('purple'),
        allDay: false,
      }, {
        title: 'Click for Google',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        backgroundColor: Metronic.getBrandColor('yellow'),
        url: 'http://google.com/',
      }]
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* validation form_sample_3
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".form_sample_3").length) return;

    var form3 = $('.form_sample_3');
    var error3 = $('.alert-danger', form3);
    var success3 = $('.alert-success', form3);

    //IMPORTANT: update CKEDITOR textarea with actual content before submit
    form3.on('submit', function() {
      for (var instanceName in CKEDITOR.instances) {
        CKEDITOR.instances[instanceName].updateElement();
      }
    });

    form3.validate({
      errorElement: 'span', //default input error message container
      errorClass: 'help-block help-block-error', // default input error message class
      focusInvalid: false, // do not focus the last invalid input
      ignore: "", // validate all fields including form hidden input
      rules: {
        nameABC: {
          minlength: 2,
          required: true
        },
        email: {
          required: true,
          email: true
        },
        options1: {
          required: true
        },
        options2: {
          required: true
        },
        select2tags: {
          required: true
        },
        datepicker: {
          required: true
        },
        occupation: {
          minlength: 5,
        },
        membership: {
          required: true
        },
        service: {
          required: true,
          minlength: 2
        },
        editor2: {
          required: true
        }
      },

      messages: { // custom messages for radio buttons and checkboxes
        membership: {
          required: "Please select a Membership type"
        },
        service: {
          required: "Please select  at least 2 types of Service",
          minlength: jQuery.validator.format("Please select  at least {0} types of Service")
        }
      },

      errorPlacement: function(error, element) { // render error placement for each input type
        if (element.parent(".input-group").size() > 0) {
          error.insertAfter(element.parent(".input-group"));
        } else if (element.attr("data-error-container")) {
          error.appendTo(element.attr("data-error-container"));
        } else if (element.parents('.radio-list').size() > 0) {
          error.appendTo(element.parents('.radio-list').attr("data-error-container"));
        } else if (element.parents('.radio-inline').size() > 0) {
          error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
        } else if (element.parents('.checkbox-list').size() > 0) {
          error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
        } else if (element.parents('.checkbox-inline').size() > 0) {
          error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
        } else {
          error.insertAfter(element); // for other inputs, just perform default behavior
        }
      },

      invalidHandler: function(event, validator) { //display error alert on form submit   
        success3.hide();
        error3.show();
        Metronic.scrollTo(error3, -200);
      },

      highlight: function(element) { // hightlight error inputs
        $(element)
          .closest('.form-group').addClass('has-error'); // set error class to the control group
      },

      unhighlight: function(element) { // revert the change done by hightlight
        $(element)
          .closest('.form-group').removeClass('has-error'); // set error class to the control group
      },

      success: function(label) {
        label
          .closest('.form-group').removeClass('has-error'); // set success class to the control group
      },

      submitHandler: function(form) {
        success3.show();
        error3.hide();
        form[0].submit(); // submit the form
      }
    });

    //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
    $('.select2me', form3).change(function() {
      form3.validate().element($(this));
       //revalidate the chosen dropdown value and show error or success message for the input
    });

    // initialize select2 tags
    $("#select2_tags").change(function() {
      form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
    }).select2({
      tags: ["red", "green", "blue", "yellow", "pink"]
    });

    $('.date-picker .form-control').change(function() {
      form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
    });
  })(jQuery);


  /* --------------------------------------------------------------------- */
  /* fullCalendar
  /* --------------------------------------------------------------------- */
  (function($) {
    // if (!$("#calendar").length) return;

    if (!jQuery().fullCalendar) return;

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var h = {};

    if (Metronic.isRTL()) {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          right: 'title, prev, next',
          center: '',
          left: 'agendaDay, agendaWeek, month, today'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          right: 'title',
          center: '',
          left: 'agendaDay, agendaWeek, month, today, prev,next'
        };
      }
    } else {
      if ($('#calendar').parents(".portlet").width() <= 720) {
        $('#calendar').addClass("mobile");
        h = {
          left: 'title, prev, next',
          center: '',
          right: 'today,month,agendaWeek,agendaDay'
        };
      } else {
        $('#calendar').removeClass("mobile");
        h = {
          left: 'title',
          center: '',
          right: 'prev,next,today,month,agendaWeek,agendaDay'
        };
      }
    }

    var initDrag = function(el) {
      // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
      // it doesn't need to have a start or end
      var eventObject = {
        title: $.trim(el.text()) // use the element's text as the event title
      };
      // store the Event Object in the DOM element so we can get to it later
      el.data('eventObject', eventObject);
      // make the event draggable using jQuery UI
      el.draggable({
        zIndex: 999,
        revert: true, // will cause the event to go back to its
        revertDuration: 0 //  original position after the drag
      });
    };

    var addEvent = function(title) {
      title = title.length === 0 ? "Untitled Event" : title;
      var html = $('<div class="external-event label label-default">' + title + '</div>');
      jQuery('#event_box').append(html);
      initDrag(html);
    };

    $('#external-events div.external-event').each(function() {
      initDrag($(this));
    });

    $('#event_add').unbind('click').click(function() {
      var title = $('#event_title').val();
      addEvent(title);
    });

    //predefined events
    $('#event_box').html("");
    addEvent("My Event 1");
    addEvent("My Event 2");
    addEvent("My Event 3");
    addEvent("My Event 4");
    addEvent("My Event 5");
    addEvent("My Event 6");

    $('#calendar').fullCalendar('destroy'); // destroy the calendar
    $('#calendar').fullCalendar({ //re-initialize the calendar
      lang: 'ko', // Customize the language for the calendar.
      header: h,
      defaultView: 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
      slotMinutes: 15,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function(date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.className = $(this).attr("data-class");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }
      },
      events: [{
        title: 'All Day Event',
        start: new Date(y, m, 1),
        backgroundColor: Metronic.getBrandColor('yellow')
      }, {
        title: 'Long Event',
        start: new Date(y, m, d - 5),
        end: new Date(y, m, d - 2),
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d - 3, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('red')
      }, {
        title: 'Repeating Event',
        start: new Date(y, m, d + 4, 16, 0),
        allDay: false,
        backgroundColor: Metronic.getBrandColor('green')
      }, {
        title: 'Meeting',
        start: new Date(y, m, d, 10, 30),
        allDay: false,
      }, {
        title: 'Lunch',
        start: new Date(y, m, d, 12, 0),
        end: new Date(y, m, d, 14, 0),
        backgroundColor: Metronic.getBrandColor('grey'),
        allDay: false,
      }, {
        title: 'Birthday Party',
        start: new Date(y, m, d + 1, 19, 0),
        end: new Date(y, m, d + 1, 22, 30),
        backgroundColor: Metronic.getBrandColor('purple'),
        allDay: false,
      }, {
        title: 'Click for Google',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        backgroundColor: Metronic.getBrandColor('yellow'),
        url: 'http://google.com/',
      }]
    });
  })(jQuery);


});


