package com.mkyong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mkyong.dao.CCCDImpl;
import com.mkyong.dao.ConfigSoCCImpl;
import com.mkyong.dao.DanSoVungImpl;
import com.mkyong.dao.DanSoXaImpl;
import com.mkyong.dao.TTDKCCCDImpl;
import com.mkyong.dao.ConfigCCCDImpl;
import com.mkyong.dao.ConfigEmailImpl;
import com.mkyong.dao.ConfigKetHonImpl;
import com.mkyong.dao.ConfigNgayNghiImpl;
import com.mkyong.dao.DanSoHuyenImpl;
import com.mkyong.dao.DanSoTinhImpl;
import com.mkyong.dao.DanTocImpl;
import com.mkyong.dao.DuyetDKKHImpl;
import com.mkyong.dao.DuyetDKKSImpl;
import com.mkyong.dao.HonNhanImpl;
import com.mkyong.dao.QuyenImpl;
import com.mkyong.dao.TTDKKetHonImpl;
import com.mkyong.dao.TTDKKhaiSinhImpl;
import com.mkyong.dao.TheCMTImpl;
import com.mkyong.dao.HuyenImpl;
import com.mkyong.dao.KhaiSinhImpl;
import com.mkyong.dao.NhomMauImpl;
import com.mkyong.dao.SoHoKhauImpl;
import com.mkyong.dao.TaiKhoanImpl;
import com.mkyong.dao.TinhImpl;
import com.mkyong.dao.XaImpl;
import com.mkyong.dao.YeuCaulmpl;
import com.mkyong.services.CCCDServiceImpl;
import com.mkyong.services.ConfigSoCCServiceImpl;
import com.mkyong.services.TTDKCCCDServiceImpl;
import com.mkyong.services.ConfigCCCDServiceImpl;
import com.mkyong.services.ConfigEmailServiceImpl;
import com.mkyong.services.ConfigKetHonServiceImpl;
import com.mkyong.services.ConfigNgayNghiServiceImpl;
import com.mkyong.services.DanTocServiceImpl;
import com.mkyong.services.HonNhanServiceImpl;
import com.mkyong.services.QuyenServiceImpl;
import com.mkyong.services.TheCMTServiceImpl;
import com.mkyong.services.HuyenServiceImpl;
import com.mkyong.services.KhaiSinhServiceImpl;
import com.mkyong.services.NhomMauServiceImpl;
import com.mkyong.services.SoHoKhauServiceImpl;
import com.mkyong.services.TaiKhoanServiceImpl;
import com.mkyong.services.TinhServiceImpl;
import com.mkyong.services.XaServiceImpl;
import com.mkyong.services.YeuCauServicelmpl;
import com.mkyong.services.function.FunctionServiceImpl;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.mkyong.web.*" })
@Import({ SecurityConfig.class })
public class AppConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/cancuoccongdan?characterEncoding=UTF-8");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("");
	    return driverManagerDataSource;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
    public CCCDImpl CCCDDao() {
        return new CCCDImpl();
    }
	
	@Bean
    public CCCDServiceImpl CCCDService() {
        return new CCCDServiceImpl();
    }
	
	@Bean
    public TTDKCCCDImpl CCCD_TamThoiDao() {
        return new TTDKCCCDImpl();
    }
	
	@Bean
    public TTDKCCCDServiceImpl CCCD_TamThoiService() {
        return new TTDKCCCDServiceImpl();
    }
	
	@Bean
    public TinhImpl Tinh() {
        return new TinhImpl();
    }
	
	@Bean
    public TinhServiceImpl TinhService() {
        return new TinhServiceImpl();
    }
	@Bean
    public HuyenImpl Huyen() {
        return new HuyenImpl();
    }
	
	@Bean
    public HuyenServiceImpl HuyenService() {
        return new HuyenServiceImpl();
    }
	
	@Bean
    public XaImpl Xa() {
        return new XaImpl();
    }
	
	@Bean
    public XaServiceImpl XaService() {
        return new XaServiceImpl();
    }
	@Bean
    public DanTocImpl DanToc() {
        return new DanTocImpl();
    }
	
	@Bean
    public DanTocServiceImpl DanTocService() {
        return new DanTocServiceImpl();
    }
	
	@Bean
    public YeuCaulmpl YeuCau() {
        return new YeuCaulmpl();
    }
	
	@Bean
    public YeuCauServicelmpl YeuCauService() {
        return new YeuCauServicelmpl();
    }
	@Bean
    public NhomMauImpl NhomMau() {
        return new NhomMauImpl();
    }
	
	@Bean
    public NhomMauServiceImpl NhomMauService() {
        return new NhomMauServiceImpl();
    }
	@Bean
    public TaiKhoanImpl TaiKhoan() {
        return new TaiKhoanImpl();
    }
	
	@Bean
    public TaiKhoanServiceImpl TaiKhoanService() {
        return new TaiKhoanServiceImpl();
    }
	@Bean
    public SoHoKhauImpl SoHoKhau() {
        return new SoHoKhauImpl();
    }
	
	@Bean
    public SoHoKhauServiceImpl SoHoKhauService() {
        return new SoHoKhauServiceImpl();
    }
	@Bean
    public TheCMTImpl DanhSachLamThe() {
        return new TheCMTImpl();
    }
	
	@Bean
    public TheCMTServiceImpl DanhSachLamTheService() {
        return new TheCMTServiceImpl();
    }
	@Bean
    public HonNhanImpl HonNhan() {
        return new HonNhanImpl();
    }
	
	@Bean
    public HonNhanServiceImpl HonNhanService() {
        return new HonNhanServiceImpl();
    }
	@Bean
    public TTDKKhaiSinhImpl TTDKKhaiSinh() {
        return new TTDKKhaiSinhImpl();
    }
	@Bean
    public KhaiSinhImpl KhaiSinh() {
        return new KhaiSinhImpl();
    }
	
	@Bean
    public KhaiSinhServiceImpl KhaiSinhService() {
        return new KhaiSinhServiceImpl();
    }
	@Bean
    public QuyenImpl Quyen() {
        return new QuyenImpl();
    }
	
	@Bean
    public QuyenServiceImpl QuyenService() {
        return new QuyenServiceImpl();
    }
	@Bean
    public TTDKKetHonImpl TTDKKetHon() {
        return new TTDKKetHonImpl();
    }
	@Bean
    public DuyetDKKHImpl DuyetDKKH() {
        return new DuyetDKKHImpl();
	}
	@Bean
    public ConfigNgayNghiServiceImpl ConfigNgayNghiService() {
        return new ConfigNgayNghiServiceImpl();
    }
	@Bean
    public ConfigNgayNghiImpl ConfigNgayNghi() {
        return new ConfigNgayNghiImpl();
    }
	@Bean
    public ConfigEmailServiceImpl ConfigEmailService() {
        return new ConfigEmailServiceImpl();
    }
	@Bean
    public ConfigEmailImpl ConfigEmail() {
        return new ConfigEmailImpl();
    }
	@Bean
    public ConfigSoCCServiceImpl ConfigSoCCService() {
        return new ConfigSoCCServiceImpl();
    }
	@Bean
    public ConfigSoCCImpl ConfigSoCC() {
        return new ConfigSoCCImpl();
    }
	@Bean
    public ConfigCCCDServiceImpl ConfigCCCDService() {
        return new ConfigCCCDServiceImpl();
    }
	@Bean
    public ConfigCCCDImpl ConfigCCCD() {
        return new ConfigCCCDImpl();
    }
	@Bean
    public FunctionServiceImpl FunctionService() {
        return new FunctionServiceImpl();
	}
    @Bean
    public ConfigKetHonServiceImpl ConfigKetHonService() {
        return new ConfigKetHonServiceImpl();
    }
	@Bean
    public ConfigKetHonImpl ConfigKetHon() {
        return new ConfigKetHonImpl();
    }
	@Bean
    public DuyetDKKSImpl DuyetDKKS() {
        return new DuyetDKKSImpl();
    }
	@Bean
    public DanSoTinhImpl DanSoTinh() {
        return new DanSoTinhImpl();
    }
	@Bean
    public DanSoHuyenImpl DanSoHuyen() {
        return new DanSoHuyenImpl();
    }
	@Bean
    public DanSoXaImpl DanSoXa() {
        return new DanSoXaImpl();
    }
	@Bean
    public DanSoVungImpl DanSoVung() {
        return new DanSoVungImpl();
    }
}