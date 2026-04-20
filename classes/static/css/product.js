document.addEventListener("DOMContentLoaded", function () {

    const thumbs = document.querySelectorAll(".thumb");
    const mainImage = document.getElementById("mainImage");

    thumbs.forEach(thumb => {
        thumb.addEventListener("click", function () {

            thumbs.forEach(t => t.classList.remove("active"));
            this.classList.add("active");

            mainImage.src = this.src;
        });
    });

});
