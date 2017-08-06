package br.com.androidarchictecture.view.details.contract

import br.com.androidarchictecture.view.base.BasePresenter

/**
 * Created by pedrohenrique on 06/08/17.
 */
interface DetailsPresenter: BasePresenter{
     fun loadDetailsCharacter(id: Long)
}
