var gulp = require('gulp');
var inject = require('gulp-inject');
var conf = require('./conf');
var mainBowerFiles = require('main-bower-files');
var fileSort = require('gulp-angular-filesort');

var browserSync = require('browser-sync');

var injectOptions = {
    ignorePath: [conf.paths.src, conf.paths.tmp],
    addRootSlash: false
};

var injectBowerOptions = {
    name: 'bower',
    relative: true
};

gulp.task('inject-reload', ['inject'], function () {
    browserSync.reload();
});

gulp.task('inject', function () {

    var injectStyle = gulp.src(conf.paths.tmp + '/app/*.css');
    var injectScript = gulp.src(conf.paths.src + conf.paths.javascript).pipe(fileSort());
    var injectBower = gulp.src(mainBowerFiles(), {read: false});

    return gulp.src(conf.paths.src + conf.paths.index)
        .pipe(inject(injectBower, injectBowerOptions))
        .pipe(inject(injectScript,injectOptions))
        .pipe(inject(injectStyle, injectOptions))
        .pipe(gulp.dest(conf.paths.tmp))
});