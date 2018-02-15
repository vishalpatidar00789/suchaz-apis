import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { ItemCommonAttributeSuchazService } from './item-common-attribute-suchaz.service';

@Component({
    selector: 'jhi-item-common-attribute-suchaz-detail',
    templateUrl: './item-common-attribute-suchaz-detail.component.html'
})
export class ItemCommonAttributeSuchazDetailComponent implements OnInit, OnDestroy {

    itemCommonAttribute: ItemCommonAttributeSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itemCommonAttributeService: ItemCommonAttributeSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItemCommonAttributes();
    }

    load(id) {
        this.itemCommonAttributeService.find(id)
            .subscribe((itemCommonAttributeResponse: HttpResponse<ItemCommonAttributeSuchaz>) => {
                this.itemCommonAttribute = itemCommonAttributeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItemCommonAttributes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemCommonAttributeListModification',
            (response) => this.load(this.itemCommonAttribute.id)
        );
    }
}
