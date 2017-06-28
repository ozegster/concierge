var gulp = require('gulp');
var sass = require('gulp-sass');
var conf = require('./conf');

var browserSync = require('browser-sync');

gulp.task('style', function () {
    styleBuild();
});

gulp.task('style-reload', ['style'], function () {
    return styleBuild()
        .pipe(browserSync.stream());
});

function styleBuild() {
    return gulp.src(conf.paths.src + conf.paths.css)
        .pipe(sass())
        .pipe(gulp.dest(conf.paths.tmp))
}
