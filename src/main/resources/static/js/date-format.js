(function (global) {
    'use strict';

    // LocalDateTime belongs to the server's clock. Do not apply browser timezone conversion.
    function formatDateTime(value, pattern) {
        if (!value) return '';
        var match = /^(\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2})(?::(\d{2})(?:\.\d+)?)?$/.exec(value);
        if (!match) return value;
        var parts = { yyyy: match[1], MM: match[2], dd: match[3], HH: match[4], mm: match[5], ss: match[6] || '00' };
        return (pattern || 'yyyy-MM-dd HH:mm').replace(/yyyy|MM|dd|HH|mm|ss/g, function (token) {
            return parts[token];
        });
    }

    function renderDates(root) {
        (root || document).querySelectorAll('[data-server-datetime]').forEach(function (element) {
            element.textContent = formatDateTime(element.dataset.serverDatetime, element.dataset.dateFormat);
        });
    }

    global.AppDate = { formatDateTime: formatDateTime, renderDates: renderDates };
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', function () { renderDates(); });
    } else {
        renderDates();
    }
}(window));
