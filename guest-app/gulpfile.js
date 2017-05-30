var gulp = require('gulp');
var webserver = require('gulp-webserver');

gulp.task('serve', function () {
    gulp.src('src')
        .pipe(webserver({
            fallback:'src/index.html',
            open:true,
            livereload: true,
            port: 8000
        }));
});
