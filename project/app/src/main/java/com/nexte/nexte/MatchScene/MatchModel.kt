package com.nexte.nexte.MatchScene

import java.time.DayOfWeek
import java.time.Month
import java.time.MonthDay
import java.time.format.DateTimeFormatter

/**
 * Created by leticia on 30/04/18.
 */

class MatchModel {

  class InitScene {

      class Request (var matchID: String)

      class Response (var match: MatchData)

      class ViewModel (var matchFormatted: FormattedMatchData)

  }
    // --------- Aux classes ---------

    class MatchData (var challenged: MatchPlayer,
                     var challenger: MatchPlayer,
                     var numberOfSets: SetsNumber)

    class MatchSet (var label: String,
                    var setsChallenged: Int,
                    var setsChallenger: Int)

    class MatchTime (var matchDay: MatchDay,
                     var matchHour: DateTimeFormatter)


    class FormattedMatchData (var challengedName: String,
                              var challengedPhoto: Int,
                              var challengerName: String,
                              var challengerPhoto: Int)

    class FormattedMatchSet (var label: String,
                             var setsChallenged: String,
                             var setsChallenger: String)

    class FormattedMatchTime (var matchDay: String,
                              var matchHour: String)


    class MatchDay (var dayOfWeek: DayOfWeek,
                    var dayOfMonth: MonthDay,
                    var month: Month)


    enum class SetsNumber (val number: Int) {
        One(1),
        Three(3),
        Five(5),
        WO(0)
    }

    class MatchPlayer (var name: String, var photo: Int)

    /*
    * var formattedFirstSet: String,
                              var formattedThirdSet: String,
                              var formattedFifthSet: String,
                              var formattedWOSet: String*/
}