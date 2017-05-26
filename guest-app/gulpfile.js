var gulp = require('gulp');
var webserver = require('gulp-webserver');

gulp.task('guest-app-home', function () {
    gulp.src('src')
        .pipe(webserver({
            fallback:'src/index.html',
            open:true,
            livereload: true
        }));
});
