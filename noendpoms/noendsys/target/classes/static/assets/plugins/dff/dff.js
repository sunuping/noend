var room = 1;

function education_fields() {

    room++;
    var objTo = document.getElementById('education_fields')
    var divtest = document.createElement("div");
    divtest.setAttribute("class", "form-group removeclass" + room);
    var rdiv = 'removeclass' + room;
    divtest.innerHTML = '<div class="row"><div class="col-sm-3 nopadding"><div class="form-group"> ' +
        '<input type="hidden" name="commodityId">' +
        '<input type="text" class="form-control" onclick="clickCommodityName(this)"  name="commodityName" value="" required placeholder="商品名称" data-toggle="modal" data-target=".modal2" readonly>' +
        ' </div></div><div class="col-sm-2 nopadding">' +
        '<div class="form-group"> <input type="text" class="form-control"  name="lotNumber" value="" placeholder="批号" required readonly>' +
        '</div></div><div class="col-sm-2 nopadding"><div class="form-group"> ' +
        '     <input type="text" class="form-control" id="norm" name="" value="" required placeholder="规格" readonly>  ' +
        '   </div></div><div class="col-sm-2 nopadding"><div class="form-group"> ' +
        '       <input type="text" class="form-control"  name="amount" value="" placeholder="数量" required>' +
        '   </div></div><div class="col-sm-2 nopadding"><div class="form-group"><div class="input-group"> ' +
        '<input type="text" class="form-control"  name="unitPrice" value="" placeholder="单价" required>' +
        '<div class="input-group-append"> <button class="btn btn-danger" type="button" onclick="remove_education_fields(' + room + ');"> <i class="fa fa-minus"></i> </button>' +
        '</div></div></div></div><div class="clear"></div></row>';

    objTo.appendChild(divtest);
}

function remove_education_fields(rid) {
    $('.removeclass' + rid).remove();
}