var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');


gulp.task('scripts', function () {
    return scriptsBuild();
});

gulp.task('scripts-reload', function () {
    return scriptsBuild()
        .pipe(browserSync.stream());
});

function scriptsBuild() {
    return gulp.src([
        path.join(conf.paths.src, '/assets/js/**/*.js'),
        path.join(conf.paths.src, '/app/**/*.module.js'),
        path.join(conf.paths.src, '/app/**/*.js'),
        path.join('!' + conf.paths.src, '/app/**/*.spec.js'),
        path.join('!' + conf.paths.src, '/app/**/*.mock.js')
    ])
}

