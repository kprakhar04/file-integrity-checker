const compareChecksum = document.getElementById("compareChecksum");
const checksum = document.getElementById("checksum");
const correct =  document.getElementById("correct");
const wrong =  document.getElementById("wrong");
const compute =  document.getElementById("compute");
const file =  document.getElementById("file");


file.addEventListener('change', e => {
	if (e.target.files[0].size/1048576 <= 100) {
		compute.disabled = false;
	} else {
		compute.disabled = true;
	}
})

const compare = e => {
	if(checksum.value.length > 0 && compareChecksum.value.length > 0) {
		const defaultCheck = "bi bi-check";
		const defaultX = "bi bi-x";
		if(checksum.value === compareChecksum.value) {
			console.log(`${defaultCheck} show`);
			correct.setAttribute('class', `${defaultCheck} show`);
			wrong.setAttribute('class', `${defaultCheck} hide`);
			console.log(correct.className);
		} else if(checksum.value !== compareChecksum.value) {
			wrong.setAttribute('class', `${defaultCheck} show`);
			correct.setAttribute('class', `${defaultCheck} hide`);
		}
	}
}