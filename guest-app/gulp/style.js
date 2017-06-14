var gulp = require('gulp');
var sass = require('gulp-sass');
var conf = require('./conf');

gulp.task('style', function () {
    return gulp.src(conf.paths.src + conf.paths.css)
        .pipe(sass())
        .pipe(gulp.dest(conf.paths.tmp))
});