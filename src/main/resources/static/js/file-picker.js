function initFilePicker(inputId, listId) {
	const input = document.getElementById(inputId);
	const listEl = document.getElementById(listId);
	if (!input || !listEl) {
		return;
	}

	let fileStore = [];

	function syncInputFiles() {
		const dt = new DataTransfer();
		fileStore.forEach((f) => dt.items.add(f));
		input.files = dt.files;
	}

	function renderList() {
		listEl.innerHTML = '';
		fileStore.forEach((f, idx) => {
			const li = document.createElement('li');
			li.className = 'file-chip';

			const nameSpan = document.createElement('span');
			nameSpan.className = 'file-chip-name';
			nameSpan.textContent = f.name;

			const removeBtn = document.createElement('button');
			removeBtn.type = 'button';
			removeBtn.className = 'file-chip-remove';
			removeBtn.textContent = '×';
			removeBtn.setAttribute('aria-label', '파일 제거');
			removeBtn.addEventListener('click', () => {
				fileStore.splice(idx, 1);
				syncInputFiles();
				renderList();
			});

			li.appendChild(nameSpan);
			li.appendChild(removeBtn);
			listEl.appendChild(li);
		});
	}

	input.addEventListener('change', () => {
		for (const f of input.files) {
			const isDuplicate = fileStore.some((existing) => existing.name === f.name && existing.size === f.size);
			if (!isDuplicate) {
				fileStore.push(f);
			}
		}
		syncInputFiles();
		renderList();
	});
}
