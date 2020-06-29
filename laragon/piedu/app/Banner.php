<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Banner extends Model
{

    protected $table = 'banners'; // chỉ định rõ bảng mà ORM liên kết tới là bảng gì 

    public function product()
    {
        return $this->hasOne('App\Product'); // tương đương với liên kết 1 1 
    }  
}
