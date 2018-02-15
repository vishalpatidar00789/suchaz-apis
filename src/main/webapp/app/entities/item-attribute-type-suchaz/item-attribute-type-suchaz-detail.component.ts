import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { ItemAttributeTypeSuchazService } from './item-attribute-type-suchaz.service';

@Component({
    selector: 'jhi-item-attribute-type-suchaz-detail',
    templateUrl: './item-attribute-type-suchaz-detail.component.html'
})
export class ItemAttributeTypeSuchazDetailComponent implements OnInit, OnDestroy {

    itemAttributeType: ItemAttributeTypeSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itemAttributeTypeService: ItemAttributeTypeSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItemAttributeTypes();
    }

    load(id) {
        this.itemAttributeTypeService.find(id)
            .subscribe((itemAttributeTypeResponse: HttpResponse<ItemAttributeTypeSuchaz>) => {
                this.itemAttributeType = itemAttributeTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItemAttributeTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemAttributeTypeListModification',
            (response) => this.load(this.itemAttributeType.id)
        );
    }
}
