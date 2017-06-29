var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

function isOnlyChange(parameters) {
    var event = parameters.event;
    return event.type === 'changed';
}

gulp.task('watch', function () {

    gulp.watch([path.join(conf.paths.src, '/*.html'), 'bower.json'], ['inject-reload']);

    gulp.watch([
        path.join(conf.paths.src, '/sass/**/*.css'),
        path.join(conf.paths.src, '/sass/**/*.scss')
    ], function(event) {
        if(isOnlyChange({event: event})) {
            gulp.start('style-reload');
        } else {
            gulp.start('inject-reload');
        }
    });

    gulp.watch(path.join(conf.paths.src, '/app/**/*.js'), function(event) {
        if(isOnlyChange({event: event})) {
            gulp.start('scripts-reload');
        } else {
            gulp.start('inject-reload');
        }
    });

    gulp.watch(path.join(conf.paths.src, '/app/**/*.html'), function(event) {
        browserSync.reload(event.path);
    });
});
