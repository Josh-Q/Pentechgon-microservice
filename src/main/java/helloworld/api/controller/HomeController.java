//package helloworld.api.controller;
//
////import javax.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletRequest;
//
//import helloworld.api.dto.GenericItemResponse;
//import helloworld.api.service.HomeService;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.data.mongodb.MongoDbFactory;
//        import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//        import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/api/v1/home")
//public class HomeController {
//
//    private final HomeService homeService;
//
//    @Autowired
//    public HomeController(HomeService homeService) {
//        this.homeService = homeService;
//    }
//
//
//    @GetMapping("")
//    public ResponseEntity<GenericItemResponse> getHome() {
//        GenericItemResponse response = new GenericItemResponse();
//        response.setData(homeService.findAll());
//        response.setMessage("All Users");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
//    //    @Autowired(required = false) DataSource dataSource;
////    @Autowired(required = false) RedisConnectionFactory redisConnectionFactory;
////    @Autowired(required = false) MongoDbFactory mongoDbFactory;
////    @Autowired(required = false) ConnectionFactory rabbitConnectionFactory;
////
////    @Autowired(required = false) ApplicationInstanceInfo instanceInfo;
//
////    @RequestMapping("/")
////    public String home(Model model) {
////        model.addAttribute("instanceInfo", instanceInfo);
////
////        if (instanceInfo != null) {
////            Map<Class<?>, String> services = new LinkedHashMap<Class<?>, String>();
////            services.put(dataSource.getClass(), toString(dataSource));
////            services.put(mongoDbFactory.getClass(), toString(mongoDbFactory));
////            services.put(redisConnectionFactory.getClass(), toString(redisConnectionFactory));
////            services.put(rabbitConnectionFactory.getClass(), toString(rabbitConnectionFactory));
////            model.addAttribute("services", services.entrySet());
////        }
////
////        return "home";
////    }
//
////    private String toString(DataSource dataSource) {
////        if (dataSource == null) {
////            return "<none>";
////        } else {
////            try {
////                Field urlField = ReflectionUtils.findField(dataSource.getClass(), "url");
////                ReflectionUtils.makeAccessible(urlField);
////                return stripCredentials((String) urlField.get(dataSource));
////            } catch (Exception fe) {
////                try {
////                    Method urlMethod = ReflectionUtils.findMethod(dataSource.getClass(), "getUrl");
////                    ReflectionUtils.makeAccessible(urlMethod);
////                    return stripCredentials((String) urlMethod.invoke(dataSource, (Object[])null));
////                } catch (Exception me){
////                    return "<unknown> " + dataSource.getClass();
////                }
////            }
////        }
////    }
////
////    private String toString(MongoDbFactory mongoDbFactory) {
////        if (mongoDbFactory == null) {
////            return "<none>";
////        } else {
////            try {
////                return mongoDbFactory.getDb().getMongo().getAddress().toString();
////            } catch (Exception ex) {
////                return "<invalid address> " + mongoDbFactory.getDb().getMongo().toString();
////            }
////        }
////    }
////
////    private String toString(RedisConnectionFactory redisConnectionFactory) {
////        if (redisConnectionFactory == null) {
////            return "<none>";
////        } else {
////            if (redisConnectionFactory instanceof JedisConnectionFactory) {
////                JedisConnectionFactory jcf = (JedisConnectionFactory) redisConnectionFactory;
////                return jcf.getHostName().toString() + ":" + jcf.getPort();
////            } else if (redisConnectionFactory instanceof LettuceConnectionFactory) {
////                LettuceConnectionFactory lcf = (LettuceConnectionFactory) redisConnectionFactory;
////                return lcf.getHostName().toString() + ":" + lcf.getPort();
////            }
////            return "<unknown> " + redisConnectionFactory.getClass();
////        }
////    }
////
////    private String toString(ConnectionFactory rabbitConnectionFactory) {
////        if (rabbitConnectionFactory == null) {
////            return "<none>";
////        } else {
////            return rabbitConnectionFactory.getHost() + ":"
////                    + rabbitConnectionFactory.getPort();
////        }
////    }
////
////    private String stripCredentials(String urlString) {
////        try {
////            if (urlString.startsWith("jdbc:")) {
////                urlString = urlString.substring("jdbc:".length());
////            }
////            URI url = new URI(urlString);
////            return new URI(url.getScheme(), null, url.getHost(), url.getPort(), url.getPath(), null, null).toString();
////        }
////        catch (URISyntaxException e) {
////            System.out.println(e);
////            return "<bad url> " + urlString;
////        }
////    }
//
//}
