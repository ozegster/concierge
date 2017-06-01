var gulp = require('gulp');
var webserver = require('gulp-webserver');
var inject = require('gulp-inject');
var mainBowerFiles = require('main-bower-files');
var runSequence = require('run-sequence');

gulp.task('serve', function () {
    gulp.src('.tmp')
        .pipe(webserver({
            fallback:'./serve/index.html',
            open:true,
            livereload: true,
            port: 8000
        }));
});

var paths = {
    javascript : 'src/app/**/*.js',
    css : ''
};

gulp.task('inject', function () {
    return gulp.src('./src/index.html')
        .pipe(inject(gulp.src(mainBowerFiles(), {read: false}), {name: 'bower', relative: true}))
        .pipe(inject(gulp.src(paths.javascript,{read:false})))
        .pipe(gulp.dest('./gulp'));
});


gulp.task('start', function () {
    runSequence('inject','serve')
});