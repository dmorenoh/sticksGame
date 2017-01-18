TSG = window.TSG || {};

TSG.StatisticsLocalStorage = function(options) {

    var _storageKey = 'TSG.stats';
    var _stats = { won: 0, lost: 0 };
    _loadStatistics();

    /**
     * Loads statistics.
     * @return {object} statistics like { won: 0, lost: 0 }
     */
    function _loadStatistics() {
        if (supportsLocalStorage()) {
            if (localStorage[_storageKey]) {
                try {
                    _stats = JSON.parse(localStorage[_storageKey]);
                } catch (e) {
                    console.log("Unable to load existing data - probably corrupted");
                }
            }
        }
        _statsChanged();
    }

    /**
     * Stores current statistics to local storage.
     */
    function _saveStatistics() {
        if (supportsLocalStorage()) {
            localStorage[_storageKey] = JSON.stringify(_stats);
        }
    }

    /**
     * @returns {boolean} true if the browser supports local storage, otherwise false
     */
    function supportsLocalStorage() {
        try {
          return 'localStorage' in window && window['localStorage'] !== null;
        } catch (e) {
          return false;
        }
    }

    function _statsChanged() {
      if (options.onChange) {
        options.onChange(_getStats());
      }
    }

    function _gameFinished(won) {
        if (won) {
            _stats.won++;
        } else {
            _stats.lost++;
        }
        _saveStatistics();
        _statsChanged();
    }

    function _getStats() {
        return { won: _stats.won, lost: _stats.lost, played: _stats.won + _stats.lost };
    }

    return {
        gameFinished: _gameFinished,
        getStats: _getStats
    };
};
