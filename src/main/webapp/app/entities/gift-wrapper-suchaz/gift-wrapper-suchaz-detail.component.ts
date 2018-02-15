import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { GiftWrapperSuchazService } from './gift-wrapper-suchaz.service';

@Component({
    selector: 'jhi-gift-wrapper-suchaz-detail',
    templateUrl: './gift-wrapper-suchaz-detail.component.html'
})
export class GiftWrapperSuchazDetailComponent implements OnInit, OnDestroy {

    giftWrapper: GiftWrapperSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private giftWrapperService: GiftWrapperSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGiftWrappers();
    }

    load(id) {
        this.giftWrapperService.find(id)
            .subscribe((giftWrapperResponse: HttpResponse<GiftWrapperSuchaz>) => {
                this.giftWrapper = giftWrapperResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInGiftWrappers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'giftWrapperListModification',
            (response) => this.load(this.giftWrapper.id)
        );
    }
}
