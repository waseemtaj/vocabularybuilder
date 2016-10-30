import ajax from "nanoajax";

export default function (searchword, callback) {
    ajax.ajax({
            url: "/api/v1/search/" + searchword
        },
        (code, text) => {
            var result = JSON.parse(text);
            callback(result);
        });
}
