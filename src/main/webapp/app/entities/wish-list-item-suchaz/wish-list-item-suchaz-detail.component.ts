import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { WishListItemSuchazService } from './wish-list-item-suchaz.service';

@Component({
    selector: 'jhi-wish-list-item-suchaz-detail',
    templateUrl: './wish-list-item-suchaz-detail.component.html'
})
export class WishListItemSuchazDetailComponent implements OnInit, OnDestroy {

    wishListItem: WishListItemSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private wishListItemService: WishListItemSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWishListItems();
    }

    load(id) {
        this.wishListItemService.find(id)
            .subscribe((wishListItemResponse: HttpResponse<WishListItemSuchaz>) => {
                this.wishListItem = wishListItemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWishListItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'wishListItemListModification',
            (response) => this.load(this.wishListItem.id)
        );
    }
}
