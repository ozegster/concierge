var gulp = require('gulp');
var sass = require('gulp-sass');
var conf = require('./conf');

var browserSync = require('browser-sync').create();

gulp.task('browserSync', function() {
    browserSync.init({
        server: {
            baseDir: 'app'
        },
    })
});

gulp.task('style', function () {
    styleBuild();
});

gulp.task('style-reload', ['style'], function () {
    return styleBuild()
        .pipe(browserSync.reload({
            stream: true
        }))
});

function styleBuild() {
    return gulp.src(conf.paths.src + conf.paths.css)
        .pipe(sass())
        .pipe(gulp.dest(conf.paths.tmp))
}
