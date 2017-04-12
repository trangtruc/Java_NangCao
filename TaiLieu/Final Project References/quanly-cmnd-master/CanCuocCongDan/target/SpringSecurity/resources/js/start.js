//Banner
    $(document).ready(function(){

      var count_banner = 4,
      timmer_banner = null;
    $('.slogan').animate({
      'opacity':1
    },5000);
    timmer_banner = setInterval(function(){

      if(count_banner < 0){
        clearInterval(timmer_banner);
        return false;
      }

      if(!Modernizr.csstransitions){
        $('.banner .photo-' + (count_banner)).animate({
          'opacity':1
        },700);

      }else{
        $('.banner .photo-' + (count_banner)).addClass('active');
      }
      count_banner--;
    },600);

    });
   