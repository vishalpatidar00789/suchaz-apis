/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { GiftWrapperSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz-dialog.component';
import { GiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.service';
import { GiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.model';

describe('Component Tests', () => {

    describe('GiftWrapperSuchaz Management Dialog Component', () => {
        let comp: GiftWrapperSuchazDialogComponent;
        let fixture: ComponentFixture<GiftWrapperSuchazDialogComponent>;
        let service: GiftWrapperSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [GiftWrapperSuchazDialogComponent],
                providers: [
                    GiftWrapperSuchazService
                ]
            })
            .overrideTemplate(GiftWrapperSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftWrapperSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftWrapperSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new GiftWrapperSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.giftWrapper = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftWrapperListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new GiftWrapperSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.giftWrapper = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'giftWrapperListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
