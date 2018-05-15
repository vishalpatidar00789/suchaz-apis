/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzMenuSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz-dialog.component';
import { SuchAzMenuSuchazService } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.service';
import { SuchAzMenuSuchaz } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.model';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz';

describe('Component Tests', () => {

    describe('SuchAzMenuSuchaz Management Dialog Component', () => {
        let comp: SuchAzMenuSuchazDialogComponent;
        let fixture: ComponentFixture<SuchAzMenuSuchazDialogComponent>;
        let service: SuchAzMenuSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzMenuSuchazDialogComponent],
                providers: [
                    ItemSuchazService,
                    SuchAzMenuSuchazService
                ]
            })
            .overrideTemplate(SuchAzMenuSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzMenuSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzMenuSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SuchAzMenuSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.suchAzMenu = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'suchAzMenuListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SuchAzMenuSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.suchAzMenu = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'suchAzMenuListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
