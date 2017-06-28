var gulp = require('gulp');
var runSequence = require('run-sequence');

gulp.task('serve',['watch'], function () {
    runSequence('clean','scripts','style','inject','openBrowser')
});

require('require-dir')('./gulp');