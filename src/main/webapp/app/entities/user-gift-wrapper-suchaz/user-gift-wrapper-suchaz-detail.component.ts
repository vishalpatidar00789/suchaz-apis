import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { UserGiftWrapperSuchaz } from './user-gift-wrapper-suchaz.model';
import { UserGiftWrapperSuchazService } from './user-gift-wrapper-suchaz.service';

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz-detail',
    templateUrl: './user-gift-wrapper-suchaz-detail.component.html'
})
export class UserGiftWrapperSuchazDetailComponent implements OnInit, OnDestroy {

    userGiftWrapper: UserGiftWrapperSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private userGiftWrapperService: UserGiftWrapperSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserGiftWrappers();
    }

    load(id) {
        this.userGiftWrapperService.find(id)
            .subscribe((userGiftWrapperResponse: HttpResponse<UserGiftWrapperSuchaz>) => {
                this.userGiftWrapper = userGiftWrapperResponse.body;
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

    registerChangeInUserGiftWrappers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userGiftWrapperListModification',
            (response) => this.load(this.userGiftWrapper.id)
        );
    }
}
