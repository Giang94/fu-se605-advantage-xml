var validate_params = new Array();

function ValidateArgs(callback_func, inp_id, optional, limit){
	this.callback_func = callback_func;
	this.inp_id = inp_id;
	this.optional = optional;
	this.limit = limit;
}

function _isFloat(x){
	var _p_xn = parseFloat(x);
	return (!isNaN(_p_xn));
}

function _isInt(x){
	var _p_nx = parseInt(x);
	return (!isNaN(_p_nx));
}

function _isFinite(x)
{
	var _p_nx = parseFloat(x);
	return (isFinite(_p_nx));
}

function _trim(s)
{	
	return s.toString().trim();
}

function _checkIfEmpty(s)
{
	return (_trim(s).length <= 0);
}

function _isFit(s, l)
{
	if (l == null || l < 0) return true;
	return (_trim(s).length <= l);
}

function _isInRange(x, b, e)
{
	var _p_xn = parseFloat(x);
	return (_p_xn >= b && _p_xn <= e);
}

function _isIn(v, set) {
	var _p_l = set.length;
	for (var _p_i = 0; _p_i < _p_l; _p_i++ )
	{
		if (v == set[_p_i]) return true;
	}
	return false;
}

function isValidString(str, isOptional, ml){		
	if (_checkIfEmpty(str) && isOptional) { return [true, ""];}
	if (_checkIfEmpty(str) && !isOptional) { return [false, "Must not be empty."];}
	if (!_isFit(str, ml)) {		
		return [false, "Must contain atmost " + ml.toString() + " characters."];
	}
	return [true, ""];
}

function isUnitPrice(dec, isOptional) {
	if (_checkIfEmpty(dec) && isOptional) { return [true, ""];}
	//if (_checkIfEmpty(dec) && !isOptional) { return [false, "Must not be empty."];}
	
	var decimal = /^\+?0*((\d{0,12}\.?0{0,2})|(\d{0,11}\.?\d{0,1})|(\d{0,10}\.?\d{0,2}))$/;  
	
	if(dec.match(decimal))   
	{   
		return [true, ""];
	}  
	else  
	{   
		return [false, "You have input a wrong format decimal. Should be at most 12 in total digit and 2 in fraction and must be positive number!"];
	}
}

/*
function isUnitPrice(dec, isOptional) {
 if (_checkIfEmpty(dec) && isOptional) { return [true, ""];}
 //Should be re-implement
 if (!_isFloat(dec)) {
  return [false, "Must be a decimal."];
 } 
 var _p_ndec = _trim(dec.toString());
 if (!_isFit(_p_ndec, 13)) {
  return [false, "Must contain atmost 12 digits."];
 }
 return [true, ""];
}
*/

function isSmallInt(x, isOptional) {
	if (_checkIfEmpty(x) && isOptional) { return [true, ""];}
	if (!_isInt(x)) {
		return [false, "Must be a mumber."];
	}
	if (!_isInRange(x, -32768, 32768)) {
		return [false, "Must have value in range [-32768, 32768]."];
	}
	return [true, ""];
}

function isDiscontinued(x, isOptional) {
	if (_checkIfEmpty(x) && isOptional) { return [true, ""];}
	if (!_isIn(parseInt(x), [0, 1])) {
		return [false, "Must take a value (0/1)."];
	}	
	return [true, ""];
}

function getText ( el ) {
	var res = el.value;
	if (res == null) res = el.innerHTML;
	if (res == null) res = el.options[el.selectedIndex].value;
	return res;
}

/*function checkValid(callback_func, inp_id, optional, limit) {
	var input_data = getText(document.getElementById(inp_id));	
	var result;
	if (limit == null){result = callback_func(input_data, optional);}
	else {result = callback_func(input_data, optional, limit);}
	return result;
}*/

function checkValid(vargs){
	var input_data = getText(document.getElementById(vargs.inp_id));	
	var result;
	if (vargs.limit == null){result = vargs.callback_func(input_data, vargs.optional);}
	else {result = vargs.callback_func(input_data, vargs.optional, vargs.limit);}
	return result;
}

function displayErr(result, err_id){
	var err_ele = document.getElementById(err_id);
	if (err_ele != null && result != null){
		err_ele.innerHTML = result[1];
		if (result[0]) {
			err_ele.style.color = "blue";		
		}
		else {
			err_ele.style.color = "red";	
		}		
	}
}

function checkAllOk(){
	var result = true;
	for (var i = 0; i < validate_params.length; i++){
		var vargs = validate_params[i];
		if (vargs != null){			
			result = result && checkValid(vargs)[0];
		}
	}
	return result;
}

function tryEnableSubmit(submit_id){
	var sub_ele = document.getElementById(submit_id);
	if (sub_ele != null) {
		if (checkAllOk()){
			sub_ele.removeAttribute("disabled");
		}
		else {
			sub_ele.setAttribute("disabled", "disabled");
		}
	}
}

function OnTextChange(vargs_index, err_id, submit_id){
	displayErr(checkValid(validate_params[vargs_index]), err_id); 
	tryEnableSubmit(submit_id);
}