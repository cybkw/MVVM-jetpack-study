package com.bkw.study.net;


import androidx.lifecycle.LiveData;

import com.bkw.network.ApiResponse;
import com.bkw.study.data.home.ArticleVO;
import com.bkw.study.data.home.BannerVO;
import com.bkw.study.data.home.PageVO;
import com.bkw.study.data.official.OfficalVO;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeApi {

    /**
     * 1.2 首页banner
     * <p>
     * https://www.wanandroid.com/banner/json
     * <p>
     * 方法：GET
     * 参数：无
     *
     * @return
     */
    @GET("banner/json")
    LiveData<ApiResponse<List<BannerVO>>> banner();
//    Observable<ApiResponse<List<BannerVO>>> banner();

    /**
     * 1.1 首页文章列表
     * <p>
     * https://www.wanandroid.com/article/list/0/json
     * <p>
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    Call<ApiResponse<PageVO<ArticleVO>>> articles(@Path("page") int page);


    /**
     * 获取公众号列表
     * <p>
     * https://wanandroid.com/wxarticle/chapters/json
     * 方法： GET
     */
    @GET("wxarticle/chapters/json")
    Call<ApiResponse<List<OfficalVO>>> officials();
}
