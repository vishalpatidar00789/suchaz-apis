import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { WishListSuchaz } from './wish-list-suchaz.model';
import { WishListSuchazService } from './wish-list-suchaz.service';

@Component({
    selector: 'jhi-wish-list-suchaz-detail',
    templateUrl: './wish-list-suchaz-detail.component.html'
})
export class WishListSuchazDetailComponent implements OnInit, OnDestroy {

    wishList: WishListSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private wishListService: WishListSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWishLists();
    }

    load(id) {
        this.wishListService.find(id)
            .subscribe((wishListResponse: HttpResponse<WishListSuchaz>) => {
                this.wishList = wishListResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWishLists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'wishListListModification',
            (response) => this.load(this.wishList.id)
        );
    }
}
