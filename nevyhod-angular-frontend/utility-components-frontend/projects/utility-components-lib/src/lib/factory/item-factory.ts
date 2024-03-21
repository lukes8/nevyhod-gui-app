import { ItemVO } from "./item-model"

export class ItemFactory {

    static makeDefault(): ItemVO {
        return new ItemVO(1, "email", "title", "category", "desc", 0, new Date, 0, 0, false, "https://hips.hearstapps.com/hmg-prod/images/766/bananas-1512135191.jpg?resize=1200:*");
    }
    static makeDefault4SecondVariant(id: number, email: string, title: string): ItemVO {
        return new ItemVO(id, email, title, "category", "desc", 0, new Date, 0, 0, false, "https://hips.hearstapps.com/hmg-prod/images/766/bananas-1512135191.jpg?resize=1200:*");
    }
    static makeDefault4ThirdVariant(id: number): ItemVO {
        return new ItemVO(id, "email", "title", "category", "desc", 0, new Date, 0, 0, false, "https://hips.hearstapps.com/hmg-prod/images/766/bananas-1512135191.jpg?resize=1200:*");
    }

    static makeDefault4List(): ItemVO[] {
        let lst: ItemVO[] = [];
        for (let i = 0; i < 10; i++) {
            lst.push(this.makeDefault4SecondVariant(i, "email"+i, "title"+i));
        }
        return lst;
    }
}