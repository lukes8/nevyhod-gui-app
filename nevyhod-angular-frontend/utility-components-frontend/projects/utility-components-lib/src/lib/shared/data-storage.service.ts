import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { RecipeService } from "./recipe.service";

@Injectable({ providedIn: 'root' })
export class DataStorageService {
    constructor(private _recipeService: RecipeService, private _http: HttpClient) {
    }

    url: string = "https://nevyhod-app-default-rtdb.firebaseio.com/";
    url2: string = this.url + "/items.json";

    storeRecipes() {
        const recipes = this._recipeService.getRecipes();
        this._http.put(this.url2, recipes)
        .subscribe(res => {
            console.log(res);
        })
    }
}