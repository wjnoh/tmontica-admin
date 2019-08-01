package com.internship.tmontica_admin.banner;

import com.internship.tmontica_admin.banner.exception.BannerException;
import com.internship.tmontica_admin.banner.exception.BannerExceptionType;
import com.internship.tmontica_admin.util.SaveImageFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerDao bannerDao;

    @Value("${menu.imagepath}")
    private String location;

    //private final JwtService jwtService;

    // 배너 등록하기
    @Transactional
    public int addBanner(Banner banner, MultipartFile multipartFile){
        // TODO : 등록하는 관리자 정보 가져오기
        String creatorId = "admin";
        banner.setCreatorId(creatorId);

        //usePage 영어로 저장
        checkUsePage(banner.getUsePage());
        String usePage = BannerUsePage.convertKoToEng(banner.getUsePage());
        banner.setUsePage(usePage);

        // 이미지 저장
        String imgUrl = SaveImageFile.saveImg(multipartFile, usePage , location);
        banner.setImgUrl(imgUrl);
        // 배너 등록
        bannerDao.addBanner(banner);

        // 같은 페이지, 같은 번호의 배너를 등록 시 기존의 배너들의 usable 변경
        bannerDao.updateBannerUnusable(banner.getNumber(), banner.getUsePage(), banner.getId());
        return banner.getId();
    }

    // usePage로 배너 가져오기
    public List<Banner> getBannersByPage(String usePage){
        checkUsePageEng(usePage);
        List<Banner> banners = bannerDao.getBannersByUsePage(usePage);
        return banners;
    }
    // 배너 수정하기

    // 배너 삭제하기

    // usepage check
    public void checkUsePage(String usePage){

        for(BannerUsePage bannerUsePage : BannerUsePage.values()){
            if(bannerUsePage.getUsePageKo().equals(usePage)){
                return;
            }
        }
        throw new BannerException(BannerExceptionType.USEPAGE_MISMATCH_EXCEPTION);
    }

    public void checkUsePageEng(String usePageEng){

        for(BannerUsePage bannerUsePage : BannerUsePage.values()){
            if(bannerUsePage.getUsePageEng().equals(usePageEng)){
                return;
            }
        }
        throw  new BannerException(BannerExceptionType.USEPAGE_MISMATCH_EXCEPTION);
    }

}
