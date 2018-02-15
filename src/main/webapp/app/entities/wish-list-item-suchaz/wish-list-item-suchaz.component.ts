import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { WishListItemSuchazService } from './wish-list-item-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-wish-list-item-suchaz',
    templateUrl: './wish-list-item-suchaz.component.html'
})
export class WishListItemSuchazComponent implements OnInit, OnDestroy {
wishListItems: WishListItemSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private wishListItemService: WishListItemSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.wishListItemService.query().subscribe(
            (res: HttpResponse<WishListItemSuchaz[]>) => {
                this.wishListItems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWishListItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WishListItemSuchaz) {
        return item.id;
    }
    registerChangeInWishListItems() {
        this.eventSubscriber = this.eventManager.subscribe('wishListItemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
