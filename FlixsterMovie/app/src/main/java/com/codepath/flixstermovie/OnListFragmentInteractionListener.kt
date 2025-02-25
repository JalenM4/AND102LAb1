package com.sarcb.flixsterplus
/**
 * This interface is used by the the RecyclerViewAdapter to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by [NowPlayingFragment]
 */
interface OnListFragmentInteractionListener {
    fun onItemClick(item: Movie)
}
