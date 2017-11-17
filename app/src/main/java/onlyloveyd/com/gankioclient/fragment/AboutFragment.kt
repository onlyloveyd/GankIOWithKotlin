package onlyloveyd.com.gankioclient.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vansuita.materialabout.builder.AboutBuilder
import onlyloveyd.com.gankioclient.R
import onlyloveyd.com.gankioclient.utils.Constant
import onlyloveyd.com.gankioclient.utils.PublicTools
import org.jetbrains.anko.share

/**
 * 文 件 名: AboutFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：关于界面
 */
class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = AboutBuilder.with(context)
                .setPhoto(R.mipmap.ic_launcher)
                .setCover(R.mipmap.profile_cover)
                .setName(getString(R.string.about_name))
                .setSubTitle(getString(R.string.about_subtitle))
                .setBrief(getString(R.string.about_brief))
                //                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(R.string.about_github)
                .addEmailLink(R.string.about_email)
                .addWebsiteLink(R.string.about_website)
                .addAndroidLink(R.string.about_android_csdn)
                //                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addAction(com.vansuita.materialabout.R.mipmap.share,
                        com.vansuita.materialabout.R.string.share_app,
                        View.OnClickListener { context?.share(Constant.APP_FIR_IM_URL) })
//                .addAction(com.vansuita.materialabout.R.mipmap.update,
//                        com.vansuita.materialabout.R.string.update_app) { PublicTools.checkUpdate(context, false) }
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build()
        return view
    }

    companion object {

        fun newInstance(): AboutFragment {
            val args = Bundle()
            val fragment = AboutFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
