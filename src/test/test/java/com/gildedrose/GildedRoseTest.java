package test.java.com.gildedrose;

import static org.junit.Assert.*;

import main.java.com.gildedrose.GildedRose;
import main.java.com.gildedrose.Item;
import org.junit.Test;

public class GildedRoseTest {
    @Test
    public void should_be_nothing_when_no_item() {
        // given (arrange)
        Item[] items = new Item[]{};

        assertEqualGildedRoseQuality(items, 0, 0);

    }

    //"판매하는 나머지 일수가 없어지면, Quality 값은 2배로 떨어집니다."
    @Test
    public void quality_drop_twice_when_sellin_is_zero() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Any", 0, 50)};

        assertEqualGildedRoseQuality(items, -1, 48);

    }

    //"Quality 값은 결코 음수가 되지는 않습니다."
    @Test
    public void quality_never_to_be_negative() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Any", 10, 0)};

        assertEqualGildedRoseQuality(items, 9, 0);

    }

    //"Quality 값은 결코 음수가 되지는 않습니다."
    @Test
    public void quality_never_to_be_negative_sellin_can_be_negative() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Any", 0, 0)};

        assertEqualGildedRoseQuality(items, -1, 0);

    }

    //"Aged Brie"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라갑니다."
    @Test
    public void quality_increase_for_aged_brie() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Aged Brie", 10, 0)};

        assertEqualGildedRoseQuality(items, 9, 1);

    }

    //"Aged Brie"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라갑니다."
    @Test
    public void quality_increase_for_aged_brie_sellin_to_be_negative() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Aged Brie", 0, 0)};

        assertEqualGildedRoseQuality(items, -1, 2);

    }

    //"Quality 값은 50를 초과 할 수 없습니다."
    @Test
    public void quality_increase_for_aged_brie_quality_max50() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};

        assertEqualGildedRoseQuality(items, 9, 50);

    }

    //"Aged Brie"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라갑니다."
    @Test
    public void quality_increase_for_aged_brie_quality_max50_negative_sellin() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Aged Brie", 0, 50)};

        assertEqualGildedRoseQuality(items, -1, 50);

    }


    //Sulfuras는 전설의 아이템이므로, 반드시 판매될 필요도 없고 Quality 값도 떨어지지 않습니다.
    @Test
    public void suffuras_not_required_to_be_sell_and_never_drop_quality() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 5, 32)};

        assertEqualGildedRoseQuality(items, 5, 32);

    }

    //Sulfuras는 전설의 아이템이므로, 반드시 판매될 필요도 없고 Quality 값도 떨어지지 않습니다.
    @Test
    public void suffuras_not_required_to_be_sell_and_never_drop_quality_negative_sellin() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 32)};

        assertEqualGildedRoseQuality(items, -1, 32);

    }

    //"Backstage passes(백스테이지 입장권)"는 "Aged Brie"와 유사하게 SellIn 값에 가까워 질수록 Quality 값이 상승
    @Test
    public void backstage_passes_quality_increase_1_util_11days_before() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)};
        assertEqualGildedRoseQuality(items, 10, 11);

    }

    //"Backstage passes(백스테이지 입장권)"는 "Aged Brie"와 유사하게 SellIn 값에 가까워 질수록 Quality 값이 상승
    @Test
    public void backstage_passes_quality_increase_1_util_11days_before_max50() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 49)};
        assertEqualGildedRoseQuality(items, 10, 50);
    }


    //10일 전까지는 매일 2 씩 증가
    @Test
    public void backstage_passes_quality_increase_2_util_10days_before() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)};
        assertEqualGildedRoseQuality(items, 9, 12);
    }

    //10일 전까지는 매일 2 씩 증가
    @Test
    public void backstage_passes_quality_increase_2_util_10days_before_max50() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)};
        assertEqualGildedRoseQuality(items, 9, 50);
    }

    //"Backstage passes(백스테이지 입장권)"는 5일 전이 되면 매일 3 씩 증가하지만, 콘서트 종료 후에는 0으로 떨어집니다.
    @Test
    public void backstage_passes_quality_increase_3_from_5days_left() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)};
        assertEqualGildedRoseQuality(items, 4, 13);
    }

    //"Backstage passes(백스테이지 입장권)"는 5일 전이 되면 매일 3 씩 증가하지만, 콘서트 종료 후에는 0으로 떨어집니다.
    @Test
    public void backstage_passes_quality_increase_3_from_5days_left_max50() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)};
        assertEqualGildedRoseQuality(items, 4, 50);
    }

    //"Backstage passes(백스테이지 입장권)"는 콘서트 종료 후에는 0으로 떨어집니다.
    @Test
    public void backstage_passes_quality_to_be_zero_at_consert_finished() {
        // given (arrange)
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 32)};

        assertEqualGildedRoseQuality(items, -1, 0);

    }

    public void assertEqualGildedRoseQuality(Item[] items, int expectedSellin, int expectedQuality) {

        GildedRose gildedRose = new GildedRose(items);

        // when (act)
        gildedRose.updateQuality();

        // then (assert)
        if (items.length > 0) {
            assertEquals(expectedSellin, items[0].sellIn);
            assertEquals(expectedQuality, items[0].quality);
        } else {
            assertEquals(expectedSellin, 0);
            assertEquals(expectedQuality, 0);
        }

    }
}
