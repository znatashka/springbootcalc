function calc() {
    $('#result-block').load('/calculation', {'expression': $('#expression').val()});
}