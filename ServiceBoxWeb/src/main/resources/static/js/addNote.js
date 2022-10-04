$('document').ready(function () {
    $('.add-note-js').on('click', function (event) {
        event.preventDefault();
        let id = $(this).attr('data-id');
        $('#orderId').val(id);
    });
});